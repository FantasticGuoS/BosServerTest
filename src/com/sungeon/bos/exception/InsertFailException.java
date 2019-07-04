package com.sungeon.bos.exception;

/**
 * 新增失败异常
 * 
 * @author GuoS
 *
 */
public class InsertFailException extends SungeonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4577465650573115393L;

	public InsertFailException(String message) {
		super(message);
	}
	
	public InsertFailException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
