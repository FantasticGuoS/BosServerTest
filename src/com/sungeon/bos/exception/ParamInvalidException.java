package com.sungeon.bos.exception;

/**
 * 参数无效异常
 * 
 * @author GuoS
 * 
 */
public class ParamInvalidException extends ParamException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327904988101723483L;

	public ParamInvalidException(String message) {
		super(message);
	}

	public ParamInvalidException(String param, String message) {
		super("parameter invalid:" + param, message);
	}

	public ParamInvalidException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
