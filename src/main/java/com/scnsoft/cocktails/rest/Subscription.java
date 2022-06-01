package com.scnsoft.cocktails.rest;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Subscription {
	
	private String endpoint;
	
	private String key;
	
	private String auth;
	
	public byte[] getKeyAsBytes() {
		return Base64.getDecoder().decode(getKey());
	}
	
	public byte[] getAuthAsBytes() {
		return Base64.getDecoder().decode(getAuth());
	}
	
	public PublicKey getUserPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		KeyFactory kf = KeyFactory.getInstance("ECDH", BouncyCastleProvider.PROVIDER_NAME);
		ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
		ECPoint point = ecSpec.getCurve().decodePoint(getKeyAsBytes());
		ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, ecSpec);

		return kf.generatePublic(pubSpec);
	}
}
