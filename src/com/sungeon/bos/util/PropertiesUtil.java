package com.sungeon.bos.util;

import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * 
 * @author GuoS
 * 
 */
public class PropertiesUtil extends Properties {

	private static final long serialVersionUID = -4296628108644641685L;
	private Logger log = Logger.getLogger(this.getClass());

	public int getInt(String key, int defaultVal) {
		try {
			return Integer.parseInt(super.getProperty(key));
		} catch (Exception e) {
			// TODO Auto-generated method stub
			log.error(e.getMessage(), e);
			return defaultVal;
		}
	}

	public Long getLong(String key, Long defaultVal) {
		try {
			return Long.parseLong(super.getProperty(key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return defaultVal;
		}
	}

	public String getString(String key, String defaultVal) {
		try {
			String value = super.getProperty(key);
			if (null == value)
				return defaultVal;
			else
				return value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return defaultVal;
		}
	}
}
