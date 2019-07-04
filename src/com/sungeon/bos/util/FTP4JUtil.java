package com.sungeon.bos.util;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import com.sungeon.bos.listener.SungeonFTPDataTransferListener;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;

/**
 * FTP4J实现FTP上传文件
 * 
 * @author Charles
 *
 */
public class FTP4JUtil {

	private static Logger log = Logger.getLogger(FTP4JUtil.class);

	// private static FTPClient ftp = null;

	public static FTPClient openConnect(String host, int port, String username, String password, String path,
			boolean isSSL) {
		try {
			FTPClient ftp = new FTPClient();

			if (isSSL) {
				TrustManager[] trustManager = new TrustManager[] { new X509TrustManager() {
					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
							throws CertificateException {
						// TODO Auto-generated method stub
					}

					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
							throws CertificateException {
						// TODO Auto-generated method stub
					}

					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						// TODO Auto-generated method stub
						return null;
					}
				} };
				SSLContext sslContext = null;
				sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustManager, new SecureRandom());
				SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
				ftp.setSSLSocketFactory(sslSocketFactory);
				ftp.setSecurity(FTPClient.SECURITY_FTPES);
			}

			ftp.connect(host, port);
			ftp.login(username, password);
			ftp.setType(FTPClient.TYPE_BINARY);
			ftp.setCharset("UTF-8");
			ftp.changeDirectory(path);

			if (ftp.isConnected())
				return ftp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("FTP地址[" + host + "]连接失败!");
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static void closeConnect(FTPClient ftp) {
		if (ftp != null) {
			if (ftp.isConnected()) {
				try {
					// ftp.logout();
					ftp.disconnect(true);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 
	 * Description: 上传文件
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
	 * @param file
	 *            本地文件
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean uploadFile(String url, int port, String username, String password, String path, File file,
			boolean isSSL) {
		SungeonFTPDataTransferListener listener = new SungeonFTPDataTransferListener(file.getName());
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, path, isSSL);

			if (null != ftp) {
				// 上传文件
				ftp.upload(file, listener);
				if (listener.getTransferStatus())
					log.info("文件[ftp://" + url + path + file.getName() + "]上传成功!");
				else
					log.info("文件" + file.getName() + "上传失败!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} finally {
			closeConnect(ftp);
		}
		return listener.getTransferStatus();
	}

	/**
	 * 
	 * Description: 批量上传文件
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
	 * @param files
	 *            本地文件List
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean uploadFiles(String url, int port, String username, String password, String path,
			List<File> files, boolean isSSL) {
		SungeonFTPDataTransferListener listener = null;
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, path, isSSL);

			if (null != ftp) {
				// 上传文件
				for (File file : files) {
					listener = new SungeonFTPDataTransferListener(file.getName());
					ftp.upload(file, listener);
					if (listener.getTransferStatus())
						log.info("文件[ftp://" + url + path + file.getName() + "]上传成功!");
					else
						log.info("文件" + file.getName() + "上传失败!");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} finally {
			closeConnect(ftp);
		}
		return listener.getTransferStatus();
	}

	/**
	 * Description: 下载文件
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
		SungeonFTPDataTransferListener listener = new SungeonFTPDataTransferListener(fileName);
		File localFile = null;
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, path, isSSL);

			if (null != ftp) {
				localFile = FileUtil.getFile(localPath + "/" + fileName);
				// 下载文件
				ftp.download(fileName, localFile, listener);
				if (listener.getTransferStatus())
					log.info("文件[ftp://" + url + path + fileName + "]下载成功，保存至本地[" + localPath + "/" + fileName + "]");
				else
					log.info("文件[ftp://" + url + path + fileName + "]下载失败!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			closeConnect(ftp);
		}
		return localFile;
	}

	/**
	 * Description: 按时间批量下载文件
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
			Date date, String localPath, boolean isSSL) {
		SungeonFTPDataTransferListener listener = new SungeonFTPDataTransferListener();
		List<File> files = new ArrayList<File>();
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, path, isSSL);

			if (null != ftp) {
				// 获取文件列表
				FTPFile[] fs = ftp.list();
				// FTPFile[] fs = ftp.list("*.txt");
				File localFile = null;
				for (FTPFile ff : fs) {
					if (null != date && ff.getModifiedDate().getTime() < date.getTime())
						continue;

					if (ff.getType() == FTPFile.TYPE_FILE) {
						listener = new SungeonFTPDataTransferListener(ff.getName());
						localFile = FileUtil.getFile(localPath + "/" + ff.getName());
						ftp.download(ff.getName(), localFile, listener);
						if (listener.getTransferStatus()) {
							log.info("文件[ftp://" + url + path + ff.getName() + "]下载成功，保存至本地[" + localPath + "/"
									+ ff.getName() + "]");
							files.add(localFile);
						} else {
							log.info("文件[ftp://" + url + path + ff.getName() + "]下载失败!");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			closeConnect(ftp);
		}
		return files;
	}

	/**
	 * Description: 删除文件
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
	public static boolean deleteFile(String url, int port, String username, String password, String path,
			String fileName, boolean isSSL) {
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, path, isSSL);

			if (null != ftp) {
				ftp.deleteFile(fileName);
				log.info("文件[ftp://" + url + path + fileName + "]删除成功!");
			}
		} catch (FTPException e) {
			// TODO: handle exception
			log.info("文件[ftp://" + url + path + fileName + "]删除失败!");
			log.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} finally {
			closeConnect(ftp);
		}
		return true;
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
	 * @param fileName
	 *            文件名
	 * @param targetPath
	 *            目标子目录
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean moveFile(String url, int port, String username, String password, String sourcePath,
			String fileName, String targetPath, boolean isSSL) {
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, sourcePath, isSSL);

			if (null != ftp) {
				ftp.rename(sourcePath + fileName, targetPath + SystemUtil.getTimeAll() + "_" + fileName);
				log.info("文件[ftp://" + url + sourcePath + fileName + "]移动至[ftp://" + url + targetPath + fileName
						+ "]成功！");
			}
		} catch (FTPException e) {
			// TODO: handle exception
			log.info("文件[ftp://" + url + sourcePath + fileName + "]移动至[ftp://" + url + targetPath + fileName + "]失败！");
			log.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} finally {
			closeConnect(ftp);
		}
		return true;
	}

	/**
	 * Description: 批量移动文件
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
	 *            文件名List
	 * @param targetPath
	 *            目标子目录
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean moveFiles(String url, int port, String username, String password, String sourcePath,
			List<String> fileNames, String targetPath, boolean isSSL) {
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, sourcePath, isSSL);

			if (null != ftp) {
				for (String fileName : fileNames) {
					ftp.rename(sourcePath + fileName, targetPath + SystemUtil.getTimeAll() + "_" + fileName);
					log.info("文件[ftp://" + url + sourcePath + fileName + "]移动至[ftp://" + url + targetPath + fileName
							+ "]成功！");
				}
			}
		} catch (FTPException e) {
			// TODO: handle exception
			log.info(
					"文件[ftp://" + url + sourcePath + fileNames + "]移动至[ftp://" + url + targetPath + fileNames + "]失败！");
			log.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} finally {
			closeConnect(ftp);
		}
		return true;
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
	 * @param path
	 *            FTP服务器上的相对路径
	 * @param sourceFileName
	 *            原文件
	 * @param targetFileName
	 *            目标文件
	 * @param isSSL
	 *            是否为SSL方式，默认为false
	 * @return
	 */
	public static boolean renameFile(String url, int port, String username, String password, String path,
			String sourceFileName, String targetFileName, boolean isSSL) {
		FTPClient ftp = null;
		try {
			ftp = openConnect(url, port, username, password, path, isSSL);

			if (null != ftp) {
				ftp.rename(sourceFileName, targetFileName);
				log.info("文件[ftp://" + url + path + sourceFileName + "]更改文件名为[ftp://" + url + path + targetFileName
						+ "]成功！");
			}
		} catch (FTPException e) {
			// TODO: handle exception
			log.info("文件[ftp://" + url + path + sourceFileName + "]更改文件名为[ftp://" + url + path + targetFileName
					+ "]失败！");
			log.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} finally {
			closeConnect(ftp);
		}
		return true;
	}

}
