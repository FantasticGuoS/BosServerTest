package com.sungeon.bos.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.sungeon.bos.entity.HttpResult;

public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);

	public final static String REQUEST_METHOD_POST = "POST";
	public final static String REQUEST_METHOD_GET = "GET";

	/**
	 * HttpURLConnection方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @return
	 */
	public static HttpResult httpGet(String requestUrl) {
		HttpURLConnection http = null;
		HttpResult result = new HttpResult();
		try {
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();
			initHttp(http, REQUEST_METHOD_GET);
			http.connect();

			result = getHttpResponse(http);
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

	/**
	 * HttpURLConnection方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @param requestData
	 *            POST方法的表单数据
	 * @return
	 */
	public static HttpResult httpPost(String requestUrl, String requestData) {
		HttpURLConnection http = null;
		HttpResult result = new HttpResult();
		try {
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();
			initHttp(http, REQUEST_METHOD_POST);

			// 当有数据需要提交时
			if (null != requestData) {
				OutputStream outputStream = http.getOutputStream();
				// 向服务端发送key = value对， 注意编码格式，防止中文乱码
				outputStream.write(requestData.getBytes("UTF-8"));
				outputStream.close();
			}

			result = getHttpResponse(http);
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

	/**
	 * HttpURLConnection方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @param headers
	 *            Header参数
	 * @param requestData
	 *            POST方法的表单数据
	 * @return
	 */
	public static HttpResult httpPost(String requestUrl, Map<String, String> headers, String requestData) {
		HttpURLConnection http = null;
		HttpResult result = new HttpResult();
		try {
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();
			initHttp(http, REQUEST_METHOD_POST);
			for (String key : headers.keySet())
				http.setRequestProperty(key, headers.get(key));

			// 当有数据需要提交时
			if (null != requestData) {
				OutputStream outputStream = http.getOutputStream();
				// 向服务端发送key = value对， 注意编码格式，防止中文乱码
				outputStream.write(requestData.getBytes("UTF-8"));
				outputStream.close();
			}

			result = getHttpResponse(http);
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

	/**
	 * HttpURLConnection方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @param jsonData
	 *            POST方法的表单数据
	 * @return
	 */
	public static HttpResult httpPost(String requestUrl, JSON jsonData) {
		HttpURLConnection http = null;
		HttpResult result = new HttpResult();
		try {
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();
			initHttp(http, REQUEST_METHOD_POST);
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Connection", "Keep-Alive");
			http.setRequestProperty("Charset", "UTF-8");

			// 当有数据需要提交时
			if (null != jsonData) {
				OutputStream outputStream = http.getOutputStream();
				// 向服务端发送key = value对， 注意编码格式，防止中文乱码
				outputStream.write(jsonData.toString().getBytes("UTF-8"));
				outputStream.close();
			}

			result = getHttpResponse(http);
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

	/**
	 * HttpURLConnection方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @param headers
	 *            Header参数
	 * @param jsonData
	 *            POST方法的表单数据
	 * @return
	 */
	public static HttpResult httpPost(String requestUrl, Map<String, String> headers, JSON jsonData) {
		HttpURLConnection http = null;
		HttpResult result = new HttpResult();
		try {
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();
			initHttp(http, REQUEST_METHOD_POST);
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Connection", "Keep-Alive");
			http.setRequestProperty("Charset", "UTF-8");

			for (String key : headers.keySet())
				http.setRequestProperty(key, headers.get(key));

			// 当有数据需要提交时
			if (null != jsonData) {
				OutputStream outputStream = http.getOutputStream();
				// 向服务端发送key = value对， 注意编码格式，防止中文乱码
				outputStream.write(jsonData.toString().getBytes("UTF-8"));
				outputStream.close();
			}

			result = getHttpResponse(http);
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

	private static void initHttp(HttpURLConnection http, String method) throws IOException {
		// 设置doOutput属性为true表示将使用此urlConnection写入数据
		http.setDoOutput(true);
		http.setDoInput(true);
		http.setUseCaches(false);
		// 设置请求方式（GET/POST）
		http.setRequestMethod(method);
		// 设置连接超时时间：3S
		http.setConnectTimeout(3000);
		// 设置读取数据超时时间：30S
		http.setReadTimeout(30000);
		// 设置授权认证
		// http.setRequestProperty("Authorization", "");
	}

	private static HttpResult getHttpResponse(HttpURLConnection http) throws IOException {
		HttpResult result = new HttpResult();
		result.setCode(http.getResponseCode());
		result.setMessage(http.getResponseMessage());

		StringBuffer buffer = new StringBuffer();
		// HTTP状态码大于400（包含400）是没有返回值的
		if (http.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
			// 获取服务端的返回值
			InputStream inputStream = http.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
		}
		result.setContent(decodeUnicode(buffer.toString()));
		return result;
	}

	/**
	 * 发送请求.
	 * 
	 * @param xmlStr
	 *            请求的数据
	 */
	public String httpsRequest(String requestUrl, String requestData) {
		StringBuffer buffer = new StringBuffer();
		HttpsURLConnection https = null;
		try {
			https = (HttpsURLConnection) (new URL(requestUrl)).openConnection();
			https.setDoInput(true);
			https.setDoOutput(true);
			https.setRequestMethod(REQUEST_METHOD_POST);
			https.setRequestProperty("Content-Length", String.valueOf(requestData.getBytes().length));
			https.setUseCaches(false);
			// 设置连接超时时间：3S
			https.setConnectTimeout(3000);
			// 设置读取数据超时时间：30S
			https.setReadTimeout(30000);
			// 设置为gbk可以解决服务器接收时读取的数据中文乱码问题
			https.getOutputStream().write(requestData.getBytes("gbk"));
			https.getOutputStream().flush();
			https.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(https.getInputStream()));

			String result = "";
			while ((result = in.readLine()) != null) {
				buffer.append(result);
			}
			in.close();
			return buffer.toString();
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			return "http连接错误：" + e.getMessage();
		} catch (MalformedURLException e) {
			// TODO: handle exception
			log.error("URL错误：" + e.getMessage(), e);
			return "URL错误：" + e.getMessage();
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			return "未知域名：" + e.getMessage();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			return "http请求超时：" + e.getMessage();
		} catch (IOException e) {
			// TODO: handle exception
			log.error("http请求失败：" + e.getMessage(), e);
			return "http请求失败：" + e.getMessage();
		} finally {
			if (https != null)
				https.disconnect();
		}
	}

	/**
	 * Unicode 转换成 中文
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * 
	 * @param fileUrl
	 *            文件http地址
	 * @param destFileName
	 *            文件保存本地目录
	 * @return
	 */
	public static HttpResult download(String fileUrl, String destFileName) {
		HttpURLConnection http = null;
		HttpResult result = new HttpResult();
		try {
			URL url = new URL(fileUrl);
			http = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			http.setConnectTimeout(3 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 得到输入流
			InputStream is = http.getInputStream();

			// 文件保存位置
			File file = FileUtil.getFile(destFileName);
			FileOutputStream fos = new FileOutputStream(file);
			int line = -1;
			byte[] fileData = new byte[1024];
			while ((line = is.read(fileData)) != -1) {
				fos.write(fileData, 0, line);
				// 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
			}
			if (fos != null) {
				fos.flush();
				fos.close();
			}
			if (is != null)
				is.close();

			result.setCode(http.getResponseCode());
			result.setMessage("File[" + url + "] Download Success");
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

	public static HttpResult upload(String requestUrl, String fileName) throws IOException {
		HttpResult result = new HttpResult();
		DataInputStream in = null;
		OutputStream out = null;
		HttpURLConnection http = null;
		InputStream ins = null;
		ByteArrayOutputStream outStream = null;
		try {
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();
			// 发送POST请求必须设置如下两行
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setRequestMethod(REQUEST_METHOD_POST);
			http.setRequestProperty("Content-Type", "text/html");
			http.setRequestProperty("Cache-Control", "no-cache");
			http.setRequestProperty("Charsert", "UTF-8");
			// http.connect();
			http.setConnectTimeout(10000);
			out = http.getOutputStream();

			File file = FileUtil.getFile(fileName);
			in = new DataInputStream(new FileInputStream(file));

			int bytes = 0;
			byte[] buffer = new byte[1024];
			while ((bytes = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytes);
			}
			out.flush();

			// 返回流
			if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ins = http.getInputStream();
				outStream = new ByteArrayOutputStream();
				byte[] data = new byte[1024];
				int count = -1;
				while ((count = ins.read(data, 0, 1024)) != -1) {
					outStream.write(data, 0, count);
				}
				data = null;
				String res = new String(outStream.toByteArray(), "UTF-8");
				System.out.println(res);
			}
			result.setCode(http.getResponseCode());
		} catch (ConnectException e) {
			// TODO: handle exception
			log.error("http连接错误：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http连接错误：" + e.getMessage());
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info("未知域名：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("未知域名：" + e.getMessage());
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			try {
				log.error("http请求失败：" + e.getMessage(), e);
				result.setCode(http.getResponseCode());
				result.setMessage("http请求失败：" + e.getMessage());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} finally {
			if (http != null)
				http.disconnect();
		}
		return result;
	}

}
