package com.sungeon.bos.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import com.sungeon.bos.entity.request.BaseRequest;

//@Component
//@Aspect
public class PortAopAdvise {

	private Logger log = Logger.getLogger(this.getClass());

	// @Pointcut("execution(* com.sungeon.bos.port.impl.*.*(..))")
	public void pointcut() {
	}

	// @Around("pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) {
		// 返回类的父类
		Object obj = null;
		try {
			if (joinPoint.getArgs().length <= 0) {
				return joinPoint.proceed();
			}
			BaseRequest req = (BaseRequest) joinPoint.getArgs()[0];
			System.out.println(req);
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return obj;
	}

}
