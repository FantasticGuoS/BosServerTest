package com.sungeon.bos.junit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.sungeon.bos.util.FileUtil;

public class TestInitSQL {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputStream input = new ClassPathResource("/sqls/initialize.sql").getInputStream();
		StringBuffer sqlSb = new StringBuffer();
		byte[] buff = new byte[1024];
		int byteRead = 0;
		while ((byteRead = input.read(buff)) != -1) {
			sqlSb.append(new String(buff, 0, byteRead));
		}
		// Windows 下换行是 \r\n, Linux 下是 \n
		String[] sqls = sqlSb.toString().split("(;\\s*\\r\\n)|(;\\s*\\n)");

		System.out.println(sqls.length);
		System.out.println(sqls[0]);
		System.out.println(sqls[1]);
		System.out.println(sqls[2]);
		System.out.println(sqls[3]);
		System.out.println("---------------------------------");
		
		System.out.println(loadInitProcedure());
	}
	
	private static List<File> loadInitProcedure() {
		List<File> files = FileUtil.listFiles("/");
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
			if (file.isDirectory())
				files.remove(file);
			if (file.getName().equals(".prc"))
				continue;
			else if (file.getName().equals(".fnc"))
				continue;
			else if (file.getName().equals(".trg"))
				continue;
			else
				files.remove(file);
		}
		return files;
	}

}
