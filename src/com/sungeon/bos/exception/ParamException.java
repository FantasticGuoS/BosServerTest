package com.sungeon.bos.exception;

/**
 * 参数异常
 * 
 * @author GuoS
 *
 */
public class ParamException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327904988101723483L;

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String param) {
		this.error = param;
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(String param, String message) {
		super(message);
		setError(param);
	}

	public ParamException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
