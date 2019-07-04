package com.sungeon.bos.listener;

import org.apache.log4j.Logger;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class SungeonFTPDataTransferListener implements FTPDataTransferListener {

	private Logger log = Logger.getLogger(this.getClass());

	private String fileName;
	private boolean transferStatus;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(boolean transferStatus) {
		this.transferStatus = transferStatus;
	}

	public SungeonFTPDataTransferListener() {
		setTransferStatus(false);
	}

	public SungeonFTPDataTransferListener(String fileName) {
		setFileName(fileName);
		setTransferStatus(false);
	}

	// 文件开始上传或下载时触发
	@Override
	public void started() {
		// TODO Auto-generated method stub
		if (getFileName().isEmpty())
			log.debug("文件开始传输");
		else
			log.debug("文件[" + getFileName() + "]开始传输");
	}

	// 文件传输完成时，触发
	@Override
	public void completed() {
		// TODO Auto-generated method stub
		if (getFileName().isEmpty())
			log.debug("文件传输完成");
		else
			log.debug("文件[" + getFileName() + "]传输完成");
		setTransferStatus(true);
	}

	// 显示已经传输的字节数
	@Override
	public void transferred(int length) {
		// TODO Auto-generated method stub
		if (getFileName().isEmpty())
			log.debug("文件完成传输的字节数：" + length);
		else
			log.debug("文件[" + getFileName() + "]完成传输的字节数：" + length);
		setTransferStatus(true);
	}

	// 传输放弃时触发
	@Override
	public void aborted() {
		// TODO Auto-generated method stub
		if (getFileName().isEmpty())
			log.debug("文件放弃传输");
		else
			log.debug("文件[" + getFileName() + "]放弃传输");
		setTransferStatus(false);
	}

	// 传输失败时触发
	@Override
	public void failed() {
		// TODO Auto-generated method stub
		if (getFileName().isEmpty())
			log.debug("文件传输失败");
		else
			log.debug("文件[" + getFileName() + "]传输失败");
		setTransferStatus(false);
	}

}
