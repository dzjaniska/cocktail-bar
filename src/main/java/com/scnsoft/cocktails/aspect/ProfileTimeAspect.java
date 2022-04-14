package com.scnsoft.cocktails.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfileTimeAspect {
	
	@Pointcut("within(@com.scnsoft.cocktails.aspect.ProfileTime *) || @annotation(com.scnsoft.cocktails.aspect.ProfileTime)")
	public void profileTime() {}
	
	@Around("profileTime()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		long timeStart = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long timeFinish = System.currentTimeMillis();
		System.out.println(joinPoint.getSignature().getName() + " execution duration: " + (timeFinish - timeStart) + " ms");
		return result;
	}
}
