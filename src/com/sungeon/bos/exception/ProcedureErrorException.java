package com.sungeon.bos.exception;

/**
 * 存储过程异常
 * 
 * @author GuoS
 *
 */
public class ProcedureErrorException extends SungeonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4577465650573115393L;

	public ProcedureErrorException(String message) {
		super(message);
	}

	public ProcedureErrorException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
