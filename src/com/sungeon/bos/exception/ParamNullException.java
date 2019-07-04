package com.sungeon.bos.exception;

/**
 * 参数为空异常
 * 
 * @author GuoS
 *
 */
public class ParamNullException extends ParamException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327904988101723483L;

	public ParamNullException(String message) {
		super(message);
	}

	public ParamNullException(String param, String message) {
		super("parameter is null:" + param, message);
	}

	public ParamNullException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
