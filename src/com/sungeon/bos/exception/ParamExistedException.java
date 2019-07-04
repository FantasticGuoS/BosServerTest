package com.sungeon.bos.exception;

/**
 * 参数已存在异常
 * 
 * @author GuoS
 *
 */
public class ParamExistedException extends ParamException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327904988101723483L;

	public ParamExistedException(String message) {
		super(message);
	}

	public ParamExistedException(String param, String message) {
		super("parameter is existed:" + param, message);
	}

	public ParamExistedException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
