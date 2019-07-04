package com.sungeon.bos.junit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sungeon.bos.util.CSVUtil;
import com.sungeon.bos.util.FileUtil;

public class TestCSV {

	public static void main(String[] args) throws IOException {
		CSVUtil csv = new CSVUtil(FileUtil.getFile("D:/Document/Excel/123.csv"), CSVUtil.TYPE_READ, ',', '"', 1, "GBK");
		String[] nextline = csv.readNextLine();
		System.out.println(Arrays.asList(nextline));
		nextline = csv.readNextLine();
		System.out.println(Arrays.asList(nextline));
		nextline = csv.readNextLine();
		System.out.println(Arrays.asList(nextline));
		csv.close();

		csv = new CSVUtil(FileUtil.getFile("D:/Document/Excel/888.csv"), CSVUtil.TYPE_WRITE, "GBK");
		List<String[]> datas =new ArrayList<String[]>();
		String[] data = new String[] { "ID", "编号", "姓名" };
		System.out.println(csv.writeOneLine(data));
		datas.add(data);
		data = new String[] { "1", "VIP123", "张三" };
		System.out.println(csv.writeOneLine(data));
		datas.add(data);
		data = new String[] { "2", "VIP124", "李四" };
		System.out.println(csv.writeOneLine(data));
		datas.add(data);
		data = new String[] { "3", "VIP125", "王五" };
		System.out.println(csv.writeOneLine(data));
		datas.add(data);
		System.out.println(csv.writeLines(datas));
		csv.close();
	}

}
