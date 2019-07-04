package com.sungeon.bos.exception;

/**
 * 参数不匹配异常
 * 
 * @author GuoS
 *
 */
public class ParamNotMatchedException extends ParamException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327904988101723483L;

	public ParamNotMatchedException(String message) {
		super(message);
	}

	public ParamNotMatchedException(String param, String message) {
		super("parameter not matched:" + param, message);
	}

	public ParamNotMatchedException(String message, RuntimeException ex) {
		super(message, ex);
	}

}
