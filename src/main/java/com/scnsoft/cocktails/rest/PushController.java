package com.scnsoft.cocktails.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@RestController
@RequestMapping("/api/push")
public class PushController {
	
	@Autowired
	private Map<String, Subscription> subscriptions;
	
	@PostMapping("/subscribe")
	@ResponseStatus(HttpStatus.CREATED)
	public void subscribe(@RequestBody Subscription subscription) {
		subscriptions.put(subscription.getEndpoint(), subscription);
	}
	
	public void sendToAllWithPayload(String payload) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
		Set<String> failedSubscriptions = new HashSet<>();
		
		for (Subscription subscription : this.subscriptions.values()) {
			boolean remove = sendPushMessage(subscription, payloadBytes);
			if (remove) {
			  failedSubscriptions.add(subscription.getEndpoint());
			}
		}
		failedSubscriptions.forEach(this.subscriptions::remove);
	}
	
	public boolean sendPushMessage(Subscription subscription, byte[] payload) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		Notification notification = new Notification(
				  subscription.getEndpoint(),
				  subscription.getUserPublicKey(),
				  subscription.getAuthAsBytes(),
			      payload
			    );
		PushService pushService = new PushService();
		
		int statusCode = pushService.send(notification).getStatusLine().getStatusCode();
		return Arrays.asList(404, 410).contains(statusCode) ? true : false;
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
