package com.sungeon.bos.exception;

/**
 * 更新失败异常
 * 
 * @author GuoS
 *
 */
public class UpdateFailException extends SungeonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414741180435721642L;

	public UpdateFailException(String message) {
		super(message);
	}

	public UpdateFailException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
