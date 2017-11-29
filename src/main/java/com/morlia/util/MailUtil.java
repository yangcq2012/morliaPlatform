package com.morlia.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailUtil extends Thread{
	
	//用于给用户发送邮件的邮箱
	//private final String from = "git@fictiongame.com";
	private final String from = "morlia@mail.dataea.com";
	
	//邮箱的用户名
	//private final String username = "git@fictiongame.com";
	//private final String username = "mgsjp@momomogame.com";
	private final String username = "morlia@mail.dataea.com";
	//邮箱的密码
	//private final String password = "bKBfbbaHfB8U6sYB";
	//private final String password = "Fiction624";
	private final String password = "5t7395y6";
	//发送邮件的服务器地址
	//private final String host = "smtp.qiye.163.com";
	//private final String host = "smtp.exmail.qq.com";
	private final String host = "qiye.emailfire.cn";
	//收件箱
	private String userAddr = "chaoqun.yang@fictiongame.com";
	//邮件标题
	private String title;
	//邮件内容
	private String info;
	
	Logger logger = Logger.getLogger(MailUtil.class);
		
	public MailUtil() {
		super();
	}

	public MailUtil(String userAddr, String title, String info) {
		super();
		this.userAddr = userAddr;
		this.title = title;
		this.info = info;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getFrom() {
		return from;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public void run() {
	try{
		System.out.println("创建邮件");
	Properties prop = new Properties();
	prop.setProperty("mail.host", host);
	prop.setProperty("mail.transport.protocol", "smtp");
	prop.setProperty("mail.smtp.auth", "true");
	
	Session session = Session.getInstance(prop);
	session.setDebug(true);
	Transport ts = session.getTransport();
	ts.connect(username, password);
	Message message = createEmail(session,userAddr);
	System.out.println("准备发送");
	ts.sendMessage(message, message.getAllRecipients());
	ts.close();
	System.out.println(userAddr+"发送成功");
	logger.info(userAddr+"发送成功");
	}catch (Exception e) {
	throw new RuntimeException(e);
	}
	}

	public synchronized Message createEmail(Session session,String user) throws Exception{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user));
		message.setSubject(title);
		message.setContent(info, "text/html;charset=UTF-8");
		message.addHeader("X-Priority", "3");
		message.addHeader("X-MSMail-Priority", "Normal");
		message.saveChanges();
		return message;
		}

}
