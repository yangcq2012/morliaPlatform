package com.morlia.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.morlia.entity.JsonResult;
import com.morlia.entity.UserInfo;
import com.morlia.exception.UserNotFoundException;
import com.morlia.service.dao.LoginService;
import com.morlia.util.ImageUtil;
import com.morlia.util.MailUtil;

@Controller
public class LoginController{
	@Resource
	private LoginService loginService;
	private String ss;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult toLogin(String account,String password,
			String code,HttpSession session) throws IOException {
		//String correctCode = (String)session.getAttribute("correctCode");
		//boolean codeState = correctCode.equalsIgnoreCase(code);
		System.out.println("ss:"+ss);
		//UserInfo userInfo = loginService.login(account, password, true);
		//session.setAttribute("userUID", userInfo.getUid());
		//session.setAttribute("username", userInfo.getUsername());
		return new JsonResult("");
	}
	
	/*
	 * 生成验证码
	 */
	@RequestMapping(value="/img.do",produces="image/png")
	@ResponseBody
	public byte[] checkcode(HttpServletResponse response,HttpSession session) 
			throws IOException {
		Object[] obj = ImageUtil.createImage();
		session.setAttribute("correctCode", obj[0]);
		BufferedImage image = (BufferedImage) obj[1];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		javax.imageio.ImageIO.write(
				image, "png", out);
		out.close();
		byte[] png = out.toByteArray();
		return png;
	}
	
	/*
	 * 找回密码
	 */
	@RequestMapping("/getEmail.do")
	public String getEmail() {
		return "WEB-INF/getEmail.html";
	}
	
	@RequestMapping("/preEmail.do")
	public String preEmail(String account,String userAddr,String code,HttpSession session) {
		UserInfo user = loginService.findUserInfoByName(account);
		if(user==null) {
			return "WEB-INF/returnIndex.html";
		}else {
			session.setAttribute("userUID", user.getUid());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userAddr", userAddr);
			return "sendEmail.do";
		}
	}
	//发送邮件
	@RequestMapping("/sendEmail.do")
	public void sendEmail(HttpServletResponse response,String userAddr,HttpSession session) throws IOException {
		long startTime = System.currentTimeMillis();
		/*response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<p><a href='http://192.168.1.239:8080/webPay/findPassword.do?startTime="
				+startTime+"'>邮件已发送</a></p>");
		pw.close();*/
		//userAddr = "chaoqun.yang@fictiongame.com";
		String title = "找回密码";
		String content = "<p><a href='http://192.168.1.239:8080/webPay/findPassword.do?startTime="
							+startTime+"'>点击此链接来重设密码</a></p>";
		MailUtil mu = new MailUtil(userAddr,title,content);
		mu.start();
		return;
	}
	//验证是否过期
	@RequestMapping("/findPassword.do")
	public String findPassword(HttpServletResponse response,HttpServletRequest request) 
			throws IOException {
		long startTime = Long.parseLong(request.getParameter("startTime"));
		long nowTime = System.currentTimeMillis();
		if(nowTime-startTime<2*60*1000) {
			return "WEB-INF/newPassword.html";
		}else {
			return "WEB-INF/returnIndex.html";
		}
	}
	//更新密码
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public UserInfo changePwd(String password,String password2,HttpSession session) {
		Integer uid = (Integer)session.getAttribute("userUID");
		String userAddr = (String)session.getAttribute("userAddr");
		UserInfo user = loginService.changePassword(uid, password, password2,userAddr);
		return user;
	}

	/*
	 * 注册功能
	 */
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String account,String password,String password2,String email,
			String code) {
		UserInfo user = loginService.regist(account, password, password2, email, true);
		return new JsonResult(user);
	}
}
