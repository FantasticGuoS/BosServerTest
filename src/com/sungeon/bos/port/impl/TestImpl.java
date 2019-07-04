package com.sungeon.bos.port.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.sungeon.bos.port.Test;

@Controller("test")
public class TestImpl extends Base implements Test {

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Double getOpera(Double a, Double b) {
		// TODO Auto-generated method stub
		log.info("a=" + a + "   b=" + b);
		return a + b;
	}

}
