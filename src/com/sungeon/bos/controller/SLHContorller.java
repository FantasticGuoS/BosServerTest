package com.sungeon.bos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/SLH")
@Controller
public class SLHContorller {

	@RequestMapping("/getCode")
	@ResponseBody
	public void getCode(String code) {
		System.out.println("code:" + code);
	}

	@RequestMapping("/getAccessToken")
	@ResponseBody
	public void getAccessToken(String access_token) {
		System.out.println("access_token:" + access_token);
	}

}
