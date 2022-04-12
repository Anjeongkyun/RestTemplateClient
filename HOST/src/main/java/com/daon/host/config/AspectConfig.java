package com.daon.host.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.daon.host.dto.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AspectConfig {
//	
//	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void getMapping(){}    
//
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
//    public void postMapping(){}
//    
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
//    public void putMapping(){}
//    
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
//    public void pacthMapping(){}
//    
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
//    public void deleteMapping(){}
//
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//    public void requestMapping(){}
//    
////    @Pointcut("execution(* com.example.base..*.*Service.*(..))")
//    @Pointcut("execution(* com.daon.host.service.ApiService.*(..))")
//    public void serviceExcution(){}
//    
//    @Around(value = "serviceExcution()")
//	public Object around(ProceedingJoinPoint pjp) throws Throwable {
//		log.debug("==========Around Logging START==========");
//		log.debug("==========Around proceed START==========");
//		Object returnValue = null;
//		try {
//			returnValue = pjp.proceed(); // 메서드 호출 자체를 감쌈					
//		} catch (Throwable e) {
//			log.debug("error : "+e);
////			returnValue = new ResponseVO<>(ResponseProtocolCode.RESPONSE_SERVER_ERROR);
//			returnValue = response.fail("오류", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		log.debug("==========Around proceed END==========");
//		log.debug("returnValue.toString() : " + returnValue);
//		log.debug("==========Around Logging END==========");	
////		return pjp;
//		return returnValue;
//	}
//
//	@Before(value = "serviceExcution()")
//	public void before(JoinPoint joinPoint) throws Throwable {				
//		log.debug("==========Before Logging START==========");
//		log.debug("==========Before Logging END==========");
//	}
//
//	@AfterReturning(pointcut = "serviceExcution()", returning = "result")
//	public void afterReturning(JoinPoint joinPoint, Object result) {			
//		log.debug("==========AfterReturning Logging START==========");
//		log.debug("result : " + result);
//		log.debug("==========AfterReturning Logging END==========");
//	}
//
//	@After(value = "serviceExcution()")
//	public void after(JoinPoint joinPoint) {			
//		log.debug("==========After Logging START==========");
//		log.debug("==========After Logging END==========");
//	}
//
//	@AfterThrowing(pointcut = "serviceExcution()", throwing = "ex")
//	public void afterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
//		log.debug("==========AfterThrowing Logging START==========");
//		log.debug("Throwable : " + ex);
//		log.debug("==========AfterThrowing Logging END==========");
//	}
}
