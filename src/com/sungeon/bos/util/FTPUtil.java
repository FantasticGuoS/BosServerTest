package com.sungeon.bos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;
import org.apache.log4j.Logger;

public class FTPUtil {

	private static Logger log = Logger.getLogger(FTPUtil.class);

	private static FTPClient ftp = null;
	private static FTPSClient ftps = null;
	private static String encoding = System.getProperty("file.encoding");

	public static boolean openConnect(String url, int port, String username, String password, String path) {
		try {
			ftp = new FTPClient();
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (port == 0)
				ftp.connect(url);
			else
				ftp.connect(url, port);// 连接FTP服务器
			// 登录
			ftp.login(username, password);
			ftp.setControlEncoding(encoding);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 检验是否连接成功
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				log.error("FTP地址[" + url + "]连接失败!");
				ftp.disconnect();
				return false;
			}

			// 转移工作目录至指定目录下
			if (null != path) {
				boolean success = ftp.changeWorkingDirectory(path);
				if (!success)
					log.error("FTP目录[" + url + path + "]转换失败!");
				return success;
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			log.error("FTP地址[" + url + "]连接失败!");
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("FTP地址[" + url + "]连接失败!");
			log.error(e.getMessage(), e);
		}
		return false;
	}

	public static boolean openSSLConnect(String url, int port, String username, String password, String path) {
		try {
			ftps = new FTPSClient(false);
			int reply;

			ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
			ftps.setAutodetectUTF8(true);

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (port == 0)
				ftps.connect(url);
			else
				ftps.connect(url, port);// 连接FTP服务器
			// 登录
			ftps.login(username, password);
			ftps.setControlEncoding(encoding);
			ftps.setFileType(FTP.BINARY_FILE_TYPE);
			// 检验是否连接成功
			reply = ftps.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				log.error("FTP地址[" + url + "]连接失败!");
				ftps.disconnect();
				return false;
			}

			// 转移工作目录至指定目录下
			if (null != path) {
				boolean success = ftps.changeWorkingDirectory(path);
				if (!success)
					log.error("FTP目录[" + url + path + "]转换失败!");
				return success;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("FTP地址[" + url + "]连接失败!");
			log.error(e.getMessage(), e);
		}
		return false;
	}

	public static void closeConnect() {
		if (ftp != null) {
			if (ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	public static void closeSSLConnect() {
		if (ftps != null) {
			if (ftps.isConnected()) {
				try {
					ftps.logout();
					ftps.disconnect();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录,如果是根目录则为“/”
	 * @param fileName
	 *            上传到FTP服务器上的文件名
	 * @param is
	 *            本地文件输入流
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String url, int port, String username, String password, String path,
			String fileName, InputStream is, boolean isSSL) {
		boolean result = false;
		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, path);
			else
				connect = openSSLConnect(url, port, username, password, path);

			if (connect) {
				if (!isSSL)
					result = ftp.storeFile(new String(fileName.getBytes(encoding), "utf-8"), // iso-8859-1
							is);
				else
					result = ftps.storeFile(new String(fileName.getBytes(encoding), "utf-8"), // iso-8859-1
							is);

				if (result)
					log.info("文件[ftp://" + url + path + fileName + "]上传成功!");
				else
					log.info("文件" + fileName + "上传失败!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return result;
	}

	/**
	 * 
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录,如果是根目录则为“/”
	 * @param filePath
	 *            本地文件的绝对路径
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean uploadFile(String url, int port, String username, String password, String path, File file,
			boolean isSSL) {
		boolean result = false;
		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, path);
			else
				connect = openSSLConnect(url, port, username, password, path);

			if (connect) {
				FileInputStream inputStream = new FileInputStream(file);
				if (!isSSL)
					result = ftp.storeFile(new String(file.getName().getBytes(encoding), "utf-8"), // iso-8859-1
							inputStream);
				else
					result = ftps.storeFile(new String(file.getName().getBytes(encoding), "utf-8"), // iso-8859-1
							inputStream);
				if (result)
					log.info("文件[ftp://" + url + path + file.getName() + "]上传成功!");
				else
					log.info("文件" + file.getName() + "上传失败!");
				inputStream.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return result;
	}

	/**
	 * Description: 在FTP服务器删除文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要删除的文件名
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean removeFile(String url, int port, String username, String password, String path,
			String fileName, boolean isSSL) {
		boolean result = false;
		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, path);
			else
				connect = openSSLConnect(url, port, username, password, path);

			if (connect) {
				if (!isSSL)
					result = ftp.deleteFile(new String(fileName.getBytes(encoding), "utf-8"));
				else
					result = ftps.deleteFile(new String(fileName.getBytes(encoding), "utf-8"));

				if (result)
					log.info("文件[ftp://" + url + path + fileName + "]删除成功!");
				else
					log.info("文件[ftp://" + url + path + fileName + "]删除失败!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static File downloadFile(String url, int port, String username, String password, String path,
			String fileName, String localPath, boolean isSSL) {
		File localFile = null;
		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, path);
			else
				connect = openSSLConnect(url, port, username, password, path);

			if (connect) {
				// 获取文件列表
				FTPFile[] fs = null;
				if (!isSSL)
					fs = ftp.listFiles();
				else
					fs = ftps.listFiles();
				boolean res = false;
				for (FTPFile ff : fs) {
					if (ff.getName().equals(fileName)) {
						localFile = new File(localPath + "/" + ff.getName());
						OutputStream os = new FileOutputStream(localFile);
						if (!isSSL)
							res = ftp.retrieveFile(ff.getName(), os);
						else
							res = ftps.retrieveFile(ff.getName(), os);
						if (res)
							log.info("文件[ftp://" + url + path + ff.getName() + "]下载成功，保存至本地[" + localPath + "/"
									+ ff.getName() + "]");
						else
							log.info("文件[ftp://" + url + path + ff.getName() + "]下载失败!");
						os.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return localFile;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器上的相对路径
	 * @param date
	 *            按文件的创建时间大于该时间的
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static List<File> downloadFiles(String url, int port, String username, String password, String path,
			final Date date, String localPath, boolean isSSL) {
		List<File> files = new ArrayList<File>();
		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, path);
			else
				connect = openSSLConnect(url, port, username, password, path);

			if (connect) {
				// 获取文件列表
				FTPFileFilter filter = new FTPFileFilter() {
					@Override
					public boolean accept(FTPFile file) {
						// TODO Auto-generated method stub
						if (file.getTimestamp().getTimeInMillis() > date.getTime())
							return true;
						else
							return false;
					}
				};
				FTPFile[] fs = null;
				if (!isSSL)
					fs = ftp.listFiles("", filter);
				else
					fs = ftps.listFiles("", filter);
				File localFile = null;
				OutputStream os = null;
				boolean res = false;
				for (FTPFile ff : fs) {
					if (ff.isDirectory())
						continue;
					localFile = FileUtil.getFile(localPath + "/" + ff.getName());
					os = new FileOutputStream(localFile);
					if (!isSSL)
						res = ftp.retrieveFile(ff.getName(), os);
					if (!isSSL)
						res = ftps.retrieveFile(ff.getName(), os);
					if (res) {
						log.info("文件[ftp://" + url + path + ff.getName() + "]下载成功，保存至本地[" + localPath + "/"
								+ ff.getName() + "]");
						files.add(localFile);
					} else
						log.info("文件[ftp://" + url + path + ff.getName() + "]下载失败!");
					os.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return files;
	}

	/**
	 * Description: 移动文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param sourcePath
	 *            原文件子目录
	 * @param fileNames
	 *            文件名列
	 * @param targetPath
	 *            目标子目录
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	@Deprecated
	public static boolean moveFilesOld(String url, int port, String username, String password, String sourcePath,
			List<String> fileNames, String targetPath, boolean isSSL) {
		boolean result = false;
		InputStream is = null;
		if (null == fileNames || fileNames.size() == 0)
			return result;

		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, sourcePath);
			else
				connect = openSSLConnect(url, port, username, password, sourcePath);

			if (connect) {
				for (String fileName : fileNames) {
					if (!isSSL) {
						ftp.changeWorkingDirectory(sourcePath);
						is = ftp.retrieveFileStream(new String(fileName.getBytes(encoding), "utf-8")); // iso-8859-1
						result = ftp.deleteFile(fileName);
					} else {
						ftps.changeWorkingDirectory(sourcePath);
						is = ftps.retrieveFileStream(new String(fileName.getBytes(encoding), "utf-8")); // iso-8859-1
						result = ftps.deleteFile(fileName);
					}
					if (result)
						log.info("原文件[ftp://" + url + sourcePath + fileName + "]删除成功！");
					else
						log.info("原文件[ftp://" + url + sourcePath + fileName + "]删除失败！");

					if (null != is) {
						if (!isSSL) {
							// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
							ftp.getReply();
							ftp.changeWorkingDirectory(targetPath);
							result = ftp.storeFile(new String(fileName.getBytes(encoding), "utf-8"), // iso-8859-1
									is);
						} else {
							// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
							ftps.getReply();
							ftps.changeWorkingDirectory(targetPath);
							result = ftps.storeFile(new String(fileName.getBytes(encoding), "utf-8"), // iso-8859-1
									is);
						}
						if (result)
							log.info("文件[ftp://" + url + sourcePath + fileName + "]移动成功！");
						else
							log.info("文件[ftp://" + url + sourcePath + fileName + "]移动失败！");
					} else
						log.info("文件[ftp://" + url + sourcePath + fileName + "]移动失败！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return result;
	}

	/**
	 * Description: 移动文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param sourcePath
	 *            原文件子目录
	 * @param fileNames
	 *            文件名列
	 * @param targetPath
	 *            目标子目录
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean moveFiles(String url, int port, String username, String password, String sourcePath,
			List<String> fileNames, String targetPath, boolean isSSL) {
		boolean result = false;
		if (null == fileNames || fileNames.size() == 0)
			return result;

		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, sourcePath);
			else
				connect = openSSLConnect(url, port, username, password, sourcePath);

			if (connect)
				for (String fileName : fileNames) {
					if (!isSSL)
						result = ftp.rename(sourcePath + fileName,
								targetPath + SystemUtil.getTimeAll() + "_" + fileName);
					else
						result = ftps.rename(sourcePath + fileName,
								targetPath + SystemUtil.getTimeAll() + "_" + fileName);
					if (result)
						log.info("文件[ftp://" + url + sourcePath + fileName + "]移动成功！");
					else
						log.info("文件[ftp://" + url + sourcePath + fileName + "]移动失败！");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return result;
	}

	/**
	 * Description: 更改文件名（也可以用作单文件移动）
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param sourceFile
	 *            原文件
	 * @param targetFile
	 *            目标文件
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean renameFile(String url, int port, String username, String password, String sourceFile,
			String targetFile, boolean isSSL) {
		boolean result = false;
		try {
			boolean connect = false;
			if (!isSSL)
				connect = openConnect(url, port, username, password, sourceFile);
			else
				connect = openSSLConnect(url, port, username, password, sourceFile);

			if (connect)
				if (!isSSL)
					result = ftp.rename(sourceFile, targetFile);
				else
					result = ftps.rename(sourceFile, targetFile);

			if (result)
				log.info("文件[ftp://" + url + sourceFile + "]更改文件名为[ftp://" + url + targetFile + "]成功！");
			else
				log.info("文件[ftp://" + url + sourceFile + "]更改文件名为[ftp://" + url + targetFile + "]失败！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			if (!isSSL)
				closeConnect();
			else
				closeSSLConnect();
		}
		return result;
	}

}
