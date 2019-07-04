package com.sungeon.bos.junit;

import java.io.File;

import com.sungeon.bos.util.Base64Util;
import com.sungeon.bos.util.FileUtil;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = FileUtil.getFile("D:/Document/aaa.jpg");
		byte[] bytes = FileUtil.readFileByBytes(file);
		
		String data = Base64Util.encode(bytes);
		System.out.println(data);
		
		FileUtil.writeFileByBytes("D:/Document/bbb.jpg", Base64Util.decode(data));
	}

}
