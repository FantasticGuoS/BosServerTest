package com.sungeon.bos.exception;

/**
 * 删除失败异常
 * 
 * @author GuoS
 *
 */
public class DeleteFailException extends SungeonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7293103426131616181L;

	public DeleteFailException(String message) {
		super(message);
	}

	public DeleteFailException(String message, RuntimeException ex) {
		super(message, ex);
	}
}
