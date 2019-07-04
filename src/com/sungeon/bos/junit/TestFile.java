package com.sungeon.bos.junit;

import java.io.IOException;

import com.sungeon.bos.util.HttpClientUtil;

public class TestFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filepath = "D:/Document/Image/aaa.jpg";
		// System.out
		// .println(HttpUtil.upload("http://127.0.0.1:8080/BosServer3.1/Rest/upload?fileName=bbb.jpg",
		// filepath));
		System.out.println(
				HttpClientUtil.upload("http://127.0.0.1:8080/BosServer3.1/Rest/upload?fileName=bbb.jpg", filepath));
	}

}
