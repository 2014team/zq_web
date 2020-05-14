package com.zq.admin.log;
 
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.zq.common.util.LogUtil;
 
 
@Aspect
@Component
public class LogInterceptor {
 
    @Pointcut("@annotation(com.zq.admin.log.LogAnnotation)")    
    public void controllerAspect() {
    	System.out.println("111111111111111111111");
    }
    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint){
    	System.out.println("2222222222222222222");
		LogUtil.logInfo(getOper(joinPoint));
	}
    
	private String getOper(JoinPoint joinPoint) {
		System.out.println("33333333333333");
		MethodSignature methodName = (MethodSignature)joinPoint.getSignature();
		Method method = methodName.getMethod();
		return method.getAnnotation(LogAnnotation.class).operateType();
	}
}