package com.spring.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceBehavior {
	
	public void chika() {
		System.out.println("이발닦아라.");
	}
	
	//joinpoint : around
	public void chikaAround(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("닦았는데");
		
		joinPoint.proceed();
		
		System.out.println("왜 또 닦아!!!");
	}

}
