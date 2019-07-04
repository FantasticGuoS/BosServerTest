package com.sungeon.bos.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sungeon.bos.dbcp.DataSourceType;
import com.sungeon.bos.dbcp.DataSourceTypeManager;

@Aspect // for aop
@Component // for auto scan
@Order(0) // execute before @Transactional
public class DataSourceInterceptor {

	@Pointcut("execution(public * com.sungeon.bos.service.sd.*.*(..))")
	public void dataSourceSecond() {
	};

	@Before("dataSourceSecond()")
	public void before(JoinPoint jp) {
		DataSourceTypeManager.set(DataSourceType.SECOND);
	}

}
