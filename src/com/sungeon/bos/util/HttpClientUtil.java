package com.sungeon.bos.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.sungeon.bos.entity.HttpResult;

public class HttpClientUtil {

	private static Logger log = Logger.getLogger(HttpClientUtil.class);

	/**
	 * HttpClient方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @return
	 */
	public static HttpResult httpGet(String requestUrl) {
		HttpResult result = new HttpResult();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(3000)
					.setConnectionRequestTimeout(30000).build();
			CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
			HttpGet http = new HttpGet(requestUrl);
			HttpResponse response = client.execute(http);

			result = getHttpResult(response);
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

	/**
	 * HttpClient方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @param requestData
	 *            POST方法的表单数据
	 * @return
	 */
	public static HttpResult httpPost(String requestUrl, String requestData) {
		HttpResult result = new HttpResult();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(3000)
					.setConnectionRequestTimeout(30000).build();
			CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
			HttpPost http = new HttpPost(requestUrl);
			if (null != requestData) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(requestData, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				http.setEntity(entity);
				// method.setHeader("Authorization", "APP_KEYS " + appkeys);
			}
			HttpResponse response = client.execute(http);
			result = getHttpResult(response);
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

	/**
	 * HttpClient方式发送Http请求
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
		HttpResult result = new HttpResult();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(3000)
					.setConnectionRequestTimeout(30000).build();
			CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
			HttpPost http = new HttpPost(requestUrl);
			if (null != requestData) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(requestData, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				http.setEntity(entity);
				for (String key : headers.keySet())
					http.setHeader(key, headers.get(key));
				// method.setHeader("Authorization", "APP_KEYS " + appkeys);
			}
			HttpResponse response = client.execute(http);
			result = getHttpResult(response);
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

	/**
	 * HttpClient方式发送Http请求
	 * 
	 * @param requestUrl
	 *            请求URL
	 * @param jsonData
	 *            POST方法的表单数据
	 * @return
	 */
	public static HttpResult httpPost(String requestUrl, JSON jsonData) {
		HttpResult result = new HttpResult();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(3000)
					.setConnectionRequestTimeout(30000).build();
			CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
			HttpPost http = new HttpPost(requestUrl);
			if (null != jsonData) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonData.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				http.setEntity(entity);
				// method.setHeader("Authorization", "APP_KEYS " + appkeys);
			}
			HttpResponse response = client.execute(http);
			result = getHttpResult(response);
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

	/**
	 * HttpClient方式发送Http请求
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
		HttpResult result = new HttpResult();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(3000)
					.setConnectionRequestTimeout(30000).build();
			CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
			HttpPost http = new HttpPost(requestUrl);
			if (null != jsonData) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonData.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				http.setEntity(entity);
				for (String key : headers.keySet())
					http.setHeader(key, headers.get(key));
				// method.setHeader("Authorization", "APP_KEYS " + appkeys);
			}
			HttpResponse response = client.execute(http);
			result = getHttpResult(response);
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

	private static HttpResult getHttpResult(HttpResponse response) throws ParseException, IOException {
		HttpResult result = new HttpResult();
		result.setCode(response.getStatusLine().getStatusCode());
		result.setMessage(response.getStatusLine().getReasonPhrase());
		result.setContent(EntityUtils.toString(response.getEntity()));
		return result;
	}

	public static HttpResult download(String fileUrl, String destFileName) {
		HttpResult result = new HttpResult();
		try {
			// 生成一个httpclient对象
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet http = new HttpGet(fileUrl);
			HttpResponse response = client.execute(http);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();

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

			result.setCode(response.getStatusLine().getStatusCode());
			result.setMessage("File[" + fileUrl + "] Download Success");
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

	public static HttpResult upload(String requestUrl, String fileName) {
		HttpResult result = new HttpResult();
		CloseableHttpClient client = HttpClients.createDefault();
		// CloseableHttpClient client = HttpClientBuilder.create().build();
		try {
			HttpPost http = new HttpPost(requestUrl);

			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000)
					.build();
			http.setConfig(requestConfig);

			File file = FileUtil.getFile(fileName);
			FileBody bin = new FileBody(file);
			StringBody comment = new StringBody("This is comment", ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment)
					.build();

			http.setEntity(reqEntity);

			System.out.println("executing request " + http.getRequestLine());
			CloseableHttpResponse response = client.execute(http);
			try {
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String responseEntityStr = EntityUtils.toString(response.getEntity());
					System.out.println(responseEntityStr);
					System.out.println("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
			result = getHttpResult(response);
			client.close();
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			log.error("http请求超时：" + e.getMessage(), e);
			result.setCode(408);
			result.setMessage("http请求超时：" + e.getMessage());
		} catch (IOException e) {
			log.error("http请求失败：" + e.getMessage(), e);
			result.setCode(400);
			result.setMessage("http请求失败：" + e.getMessage());
		}
		return result;
	}

}
