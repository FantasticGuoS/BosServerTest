package com.sungeon.bos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.port.impl.Base;

@Controller
public class BaseContorller extends Base {

	@Autowired
	protected HttpServletRequest req;
	@Autowired
	protected HttpServletResponse resp;

	/**
	 * 获取Http中所有Header
	 * 
	 * @return
	 */
	protected JSONObject getHeaders() {
		JSONObject headers = new JSONObject();
		Enumeration<String> ens = req.getHeaderNames();
		while (ens.hasMoreElements()) {
			String key = ens.nextElement();
			String value = req.getHeader(key);
			headers.put(key, value);
		}
		return headers;
	}

	/**
	 * 获取Http中单个Header
	 * 
	 * @return
	 */
	protected String getHeader(String header) {
		return req.getHeader(header);
	}

	/**
	 * 获取Http中所有Cookie
	 * 
	 * @return
	 */
	protected JSONObject getCookies() {
		JSONObject cookies = new JSONObject();

		Cookie[] cooks = req.getCookies();
		if (null != cooks)
			for (Cookie cook : cooks)
				cookies.fluentPut(cook.getName(), cook.getValue());

		return cookies;
	}

	/**
	 * 获取Http中所有Session
	 * 
	 * @return
	 */
	protected JSONObject getSessions() {
		JSONObject sessions = new JSONObject();
		Enumeration<String> ens = req.getSession().getAttributeNames();
		while (ens.hasMoreElements()) {
			String key = ens.nextElement();
			String value = (String) req.getSession().getAttribute(key);
			sessions.put(key, value);
		}
		return sessions;
	}

	/**
	 * 获取Http中单个Session
	 * 
	 * @return
	 */
	protected String getSession(String session) {
		return (String) req.getSession().getAttribute(session);
	}

	/**
	 * 获取Http中的Post参数
	 * 
	 * @return
	 */
	protected String getPostData() {
		BufferedReader reader = null;
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		try {
			is = req.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null)
				sb.append(line + "\n");

			return sb.toString().replace("\n", "").trim();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
