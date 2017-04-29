package com.huaxia.framework.common.utils;

import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpSession;

/**
 * FTP 客户端链接工具类，通过DefaultFtpSessionFactory创建一个
 * FtpSession 工具，此后用FtpSession对象操作ftp服务器
 * @author shilei
 *
 */
public class FTPUtils {

	private static DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
	
	/**
	 * 创建新的到ftp服务器的链接并登录
	 * @param host String ftp服务器地址或者主机名
	 * @param port int ftp服务器链接端口
	 * @param username String ftp登录名称
	 * @param password String ftp登录密码
	 * @return FtpSession 一个ftp客户端的seeion对象
	 */
	public static synchronized FtpSession createFtpSession(String host, int port, String username, String password) {
		if (ftpSessionFactory == null){
			ftpSessionFactory = new DefaultFtpSessionFactory();
		}
		ftpSessionFactory.setHost(host);
		ftpSessionFactory.setPort(port);
		ftpSessionFactory.setUsername(username);
		ftpSessionFactory.setPassword(password);
		return ftpSessionFactory.getSession();
	}
	
	/**
	 * 关闭FtpSession与服务器的链接
	 * @param ftpSession FtpSession对象
	 */
	public static void closeSession(FtpSession ftpSession){
		if (ftpSession != null && ftpSession.isOpen()){
			ftpSession.close();
		}
	}
}
