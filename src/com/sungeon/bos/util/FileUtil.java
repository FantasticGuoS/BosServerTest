package com.sungeon.bos.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * File工具类
 * 
 * @author GuoS
 * @create 2013-3-29 下午2:40:14
 */
public class FileUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	/**
	 * 根据文件绝对路劲读取文件
	 * 
	 * @param fileName
	 *            文件路径
	 * @return
	 * @throws IOException
	 */
	public static File getFile(String fileName) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				file.createNewFile();
				log.info("文件[" + fileName + "]不存在并创建成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return file;
	}

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * @param fileName
	 *            文件路径
	 */
	public static byte[] readFileByBytes(String fileName) {
		try {
			FileInputStream in = new FileInputStream(getFile(fileName));
			byte[] bytes = getFileContentByBytes(in);
			in.close();
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * @param file
	 *            文件
	 */
	public static byte[] readFileByBytes(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] bytes = getFileContentByBytes(in);
			in.close();
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	private static byte[] getFileContentByBytes(FileInputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
		byte[] cache = new byte[CACHE_SIZE];
		int nRead = 0;
		while ((nRead = in.read(cache)) != -1) {
			out.write(cache, 0, nRead);
			out.flush();
		}
		out.close();
		return out.toByteArray();
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * @param fileName
	 *            文件路径
	 * @param chrset
	 *            编码格式
	 * @return
	 */
	public static String readFileByChar(String fileName, String chrset) {
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(getFile(fileName)),
					null == chrset ? "utf-8" : chrset);
			String content = getFileContentByChar(reader);
			reader.close();
			return content;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * @param file
	 *            文件
	 * @param chrset
	 *            编码格式
	 * @return
	 */
	public static String readFileByChar(File file, String chrset) {
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file),
					null == chrset ? "utf-8" : chrset);
			String content = getFileContentByChar(reader);
			reader.close();
			return content;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	private static String getFileContentByChar(InputStreamReader reader) throws IOException {
		StringBuffer sb = new StringBuffer();
		int tempchar;
		while ((tempchar = reader.read()) != -1) {
			// 对于windows下，rn这两个字符在一起时，表示一个换行。
			// 但如果这两个字符分开显示时，会换两次行。
			// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
			// if (((char) tempchar) != 'r')
			sb.append((char) tempchar);

		}
		return sb.toString();
	}

	/**
	 * 以行为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * @param fileName
	 *            文件路径
	 * @param chrset
	 *            编码格式
	 * @return
	 */
	public static String readFileByLine(String fileName, String chrset) {
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(getFile(fileName)),
					null == chrset ? "utf-8" : chrset);
			String content = getFileContentByLine(reader);
			reader.close();
			return content;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 以行为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * @param file
	 *            文件
	 * @param chrset
	 *            编码格式
	 * @return
	 */
	public static String readFileByLine(File file, String chrset) {
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file),
					null == chrset ? "utf-8" : chrset);
			String content = getFileContentByLine(reader);
			reader.close();
			return content;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	private static String getFileContentByLine(InputStreamReader reader) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer();
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null)
			sb.append(lineTxt);
		bufferedReader.close();
		return sb.toString();
	}

	/**
	 * 抓取指定目录下的所有文件
	 * 
	 * @param path
	 *            目录
	 * @return
	 */
	public static List<File> listFiles(String path) {
		List<File> files = new ArrayList<File>();
		File file = new File(path);
		if (!file.exists()) {
			log.error(path + " 路劲不存在");
			return files;
		}
		File[] filearray = file.listFiles();
		files = Arrays.asList(filearray);
		return files;
	}

	/**
	 * 以文件流方式写入文件
	 * 
	 * @param fileName
	 *            文件路径
	 * @param in
	 *            文件流
	 * @return
	 */
	public static File writeFileByInputStream(String fileName, InputStream in) {
		try {
			File destFile = getFile(fileName);
			OutputStream out = new FileOutputStream(destFile);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			in.close();
			return destFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 以字节方式写入文件
	 * 
	 * @param fileName
	 *            文件路径
	 * @param bytes
	 *            文件内容（字节）
	 * @return
	 */
	public static File writeFileByBytes(String fileName, byte[] bytes) {
		InputStream in = new ByteArrayInputStream(bytes);
		return writeFileByInputStream(fileName, in);
	}

	/**
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            文件内容
	 * @param chrset
	 *            编码
	 * @return
	 */
	public static File writeFile(String fileName, String content, String chrset) {
		FileOutputStream out;
		try {
			File destFile = getFile(fileName);
			out = new FileOutputStream(destFile);
			BufferedOutputStream buff = new BufferedOutputStream(out);
			buff.write(content.getBytes(null == chrset ? "utf-8" : chrset));
			buff.flush();
			buff.close();
			out.close();
			return destFile;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 文件内容为XML格式转为JavaBean
	 * 
	 * @param fileName
	 *            文件路径
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T> T fileToBeanFromXML(String fileName, Class<T> clazz) {
		return fileToBeanFromXML(getFile(fileName), clazz);
	}

	/**
	 * 文件内容为XML格式转为JavaBean
	 * 
	 * @param file
	 *            文件
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T> T fileToBeanFromXML(File file, Class<T> clazz) {
		return JaxbUtil.converyToBean(readFileByLine(file, null), clazz);
	}

	/**
	 * 文件内容为JSONObject格式转为JavaBean
	 * 
	 * @param fileName
	 *            文件路径
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T> T fileToBeanFromJSONObject(String fileName, Class<T> clazz) {
		return fileToBeanFromJSONObject(getFile(fileName), clazz);
	}

	/**
	 * 文件内容为JSONObject格式转为JavaBean
	 * 
	 * @param file
	 *            文件
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T> T fileToBeanFromJSONObject(File file, Class<T> clazz) {
		return JSONObject.parseObject(readFileByLine(file, null), clazz);
	}

	/**
	 * 文件内容为JSONOArray格式转为List
	 * 
	 * @param fileName
	 *            文件路径
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T> List<T> filetoListFromJSONArray(String fileName, Class<T> clazz) {
		return filetoListFromJSONArray(getFile(fileName), clazz);
	}

	/**
	 * 文件内容为JSONOArray格式转为List
	 * 
	 * @param file
	 *            文件
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T> List<T> filetoListFromJSONArray(File file, Class<T> clazz) {
		return JSONArray.parseArray(readFileByLine(file, null), clazz);
	}

}
