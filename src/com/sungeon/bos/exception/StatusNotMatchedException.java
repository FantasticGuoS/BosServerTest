package com.sungeon.bos.exception;

/**
 * 状态不匹配异常
 * 
 * @author GuoS
 *
 */
public class StatusNotMatchedException extends SungeonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4452193648611878503L;

	public StatusNotMatchedException(String message) {
		super(message);
	}

	public StatusNotMatchedException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
