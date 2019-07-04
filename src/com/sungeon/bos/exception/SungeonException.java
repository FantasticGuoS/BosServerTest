package com.sungeon.bos.exception;

/**
 * Sungeon异常
 * 
 * @author GuoS
 *
 */
public class SungeonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 136158246874425010L;

	public SungeonException(String message) {
		super(message);
	}

	public SungeonException(String message, RuntimeException ex) {
		super(message, ex);
	}
}
