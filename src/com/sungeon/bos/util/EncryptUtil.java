package com.sungeon.bos.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.request.BaseRequest;

/**
 * 加密算法工具类
 * 
 * @author GuoS
 * 
 */
public class EncryptUtil {

	private static Logger log = Logger.getLogger(EncryptUtil.class);
	public static String query = null;

	private static String byteArray2HexString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				buffer.append("0");
			}
			buffer.append(temp);
		}
		return buffer.toString();
	}

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            待加密的报文
	 * @param isUpper
	 *            是否转为大写
	 * @return
	 */
	public static String MD5(String str, boolean isUpper) {
		if (null == str || str.length() == 0)
			return null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			byte bytes[] = md.digest();

			if (isUpper)
				return byteArray2HexString(bytes).toUpperCase();
			else
				return byteArray2HexString(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * SHA1加密
	 * 
	 * @param str
	 *            待加密的报文
	 * @param isUpper
	 *            是否转为大写
	 * @return
	 */
	public static String SHA1(String str, boolean isUpper) {
		if (null == str || str.length() == 0)
			return null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(str.getBytes("UTF-8"));
			byte bytes[] = md.digest();

			if (isUpper)
				return byteArray2HexString(bytes).toUpperCase();
			else
				return byteArray2HexString(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * SHA256加密
	 * 
	 * @param str
	 *            待加密的报文
	 * @param isUpper
	 *            是否转为大写
	 * @return
	 */
	public static String SHA256(String str, boolean isUpper) {
		if (null == str || str.length() == 0)
			return null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes("UTF-8"));
			byte bytes[] = md.digest();

			if (isUpper)
				return byteArray2HexString(bytes).toUpperCase();
			else
				return byteArray2HexString(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * T3生成签名
	 * 
	 * @param req
	 *            参数
	 * @param secret
	 *            密钥
	 * @return
	 */
	public static String signToRequest(BaseRequest req, String secret) {
		StringBuffer param = new StringBuffer();
		try {
			param.append("appid=" + Base64Util.encode(req.getAppid().getBytes("UTF-8")));
			param.append("&appsecret=" + Base64Util.encode(secret.getBytes("UTF-8")));
			param.append("&version=" + Base64Util.encode(req.getVersion().getBytes("UTF-8")));
			param.append("&method=" + Base64Util.encode(req.getMethod().getBytes("UTF-8")));
			param.append("&timestamp=" + Base64Util.encode(req.getTimestamp().getBytes("UTF-8")));
			query = param.toString();
			return MD5(query, true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return "";
		}
	}

	/**
	 * T3生成签名
	 * 
	 * @param json
	 *            参数
	 * @param secret
	 *            密钥
	 * @return
	 */
	public static String signToRequest(JSONObject json, String secret) {
		BaseRequest req = JSON.toJavaObject(json, BaseRequest.class);
		return signToRequest(req, secret);
	}

	/**
	 * T2生成签名
	 * 
	 * @param req
	 *            参数
	 * @param secret
	 *            密钥，拼接在参数头
	 * @param qhs
	 *            是否需要在末尾添加密钥
	 * @return
	 */
	public static String signToRequest(BaseRequest req, String secret, boolean qhs) {
		JSONObject json = JSONObject.parseObject(req.toString());
		return signToRequest(json, secret, qhs);
	}

	/**
	 * T2生成签名
	 * 
	 * @param json
	 *            参数
	 * @param secret
	 *            密钥，拼接在参数头
	 * @param qhs
	 *            是否需要在末尾添加密钥
	 * @return
	 */
	public static String signToRequest(JSONObject json, String secret, boolean qhs) {
		@SuppressWarnings("unchecked")
		TreeMap<String, Object> map = JSON.parseObject(json.toString(), TreeMap.class);
		return signToRequest(map, secret, qhs);
	}

	/**
	 * T2生成签名
	 * 
	 * @param map
	 *            参数
	 * @param secret
	 *            密钥，拼接在参数头
	 * @param qhs
	 *            是否需要在末尾添加密钥
	 * @return
	 */
	public static String signToRequest(TreeMap<String, Object> map, String secret, boolean qhs) {
		// 第一步：把字典按Key的字母顺序排序
		Iterator<String> iterator = map.keySet().iterator();
		// 第二步：把所有参数名和参数值串在一起
		StringBuffer param = new StringBuffer(secret);
		while (iterator.hasNext()) {
			String key = iterator.next().trim();
			Object value = map.get(key);
			String val = "";
			if (value instanceof String)
				val = ((String) value).trim();
			if (value instanceof Integer)
				val = value.toString().trim();
			if (value instanceof Double)
				val = value.toString().trim();

			if (!key.isEmpty() && !key.equals("sign") && !val.isEmpty())
				param.append(key).append(val);
		}
		if (qhs)
			param.append(secret);
		// 第三步：使用MD5加密
		MessageDigest md5;
		byte[] bytes = null;
		try {
			md5 = MessageDigest.getInstance("md5");
			bytes = md5.digest(param.toString().getBytes("utf-8"));
			query = param.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}
		// 第四步：把二进制转化为大写的十六进制
		return byteArray2HexString(bytes);
	}

}