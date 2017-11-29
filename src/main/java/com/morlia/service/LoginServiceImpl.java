package com.morlia.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.morlia.dao.FindUserDao;
import com.morlia.entity.UserInfo;
import com.morlia.exception.RegistFaildException;
import com.morlia.exception.UserNotFoundException;
import com.morlia.service.dao.LoginService;
import com.morlia.util.UserUtil;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource(name="findUserDao")
	private FindUserDao dao;

	public UserInfo login(String account, String password, boolean codeState) 
			throws UserNotFoundException {
		UserInfo userInfo = new UserInfo();
		//1、账号不能为空
		if(account==null||account.trim().isEmpty()) {
			throw new UserNotFoundException("账户名为空");
		}
		account = account.trim();
		//2、密码不能为空
		if(password==null||password.trim().isEmpty()) {
			throw new UserNotFoundException("密码为空");
		}
		//密码长度小于6位
		if(password.length()<6) {
			throw new UserNotFoundException("密码长度过短");
		}		
		//3、查找账号
		userInfo = findUserInfoByName(account);
		//4、判断账号是否存在
		if(userInfo.getUid()==null) {
			throw new UserNotFoundException("账号不存在");
		}
		System.out.println(userInfo);
		//5、判断账户权限
		//int uid = userInfo.getUid();

		if(!userInfo.getPassword().equals(password)) {
			throw new UserNotFoundException("密码错误");
		}
		if(!codeState){
			throw new UserNotFoundException("验证码错误");
		}
		//dao.updateAccessed(uid);
		return userInfo;
	}
	
	public UserInfo findUserInfoByName(String account) {
		UserInfo userInfo = new UserInfo();
		//账号为邮箱
		if(UserUtil.isEmail(account)) {
			userInfo.setEmail(account);
			userInfo = dao.findUser(userInfo);
		}else if(UserUtil.isDigitsString(account)) {//账号为username
			userInfo.setUsername(account);
			userInfo = dao.findUser(userInfo);
			//用username为查到
			if(userInfo==null) {
				userInfo = new UserInfo();
				try {
					int aUid = Integer.parseInt(account);
					userInfo.setUid(aUid);
					userInfo = dao.findUser(userInfo);
				} catch (Exception e) {//强转失败，则账号错误
					throw new UserNotFoundException("账号异常");
				}
			}
		}else {
			String[] aField = account.split("@");
			//不是有且只有一个@，则账号错误
			if(2!=aField.length) {
				userInfo.setUsername(account);
				userInfo = dao.findUser(userInfo);
			}else {
				String aPrefix = aField[0];
				String aSuffix = aField[1];
				userInfo.setDevice("");
				userInfo.setOs("");
				userInfo.setGuest("");
				userInfo.setFacebook("");
				userInfo.setGoogle("");
				if("yk".equals(aSuffix)) {
					throw new UserNotFoundException("不支持游客登录，请先注册");
				}else if("facebook".equals(aSuffix)) {
					throw new UserNotFoundException("账号异常");
				}else if("google".equals(aSuffix)) {
					userInfo.setGoogle(aPrefix);
					userInfo = dao.findUser(userInfo);
				}else {
					userInfo.setFacebook(aSuffix);
					userInfo = dao.findUser(userInfo);
				}
			}
			
		}
		return userInfo;
	}

	public UserInfo regist(String account, String password,String password2, String email
			,boolean codeState) throws RegistFaildException {
		//用户名判断
		if(account==null||account.trim().isEmpty()) {
			throw new RegistFaildException("用户名空");
		}
		account = account.trim();
		if(!UserUtil.isUsername(account)) {
			throw new RegistFaildException("用户名不符合规范");
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(account);
		userInfo = dao.findUser(userInfo);
		if(userInfo!=null) {
			throw new RegistFaildException("用户名已存在");
		}
		//密码判断
		if(password==null||password.trim().isEmpty()) {
			throw new RegistFaildException("密码不能为空");
		}
		password = password.trim();
		if(password.length()<6) {
			throw new RegistFaildException("密码长度不能小于6位");
		}
		//确认密码判断
		if(password2==null||password2.trim().isEmpty()) {
			throw new RegistFaildException("确认密码不能为空");
		}
		password2 = password2.trim();
		if(!password.equals(password2)) {
			throw new RegistFaildException("前后密码不一致");
		}
		//验证码判断
		if(!codeState) {
			throw new RegistFaildException("验证码错误");
		}
		//邮箱处理
		if(email==null||email.trim().isEmpty()) {
			email = "";
		}
		UserInfo user = new UserInfo();
		user.setUsername(account);
		user.setPassword(password);
		user.setEmail(email);
		dao.saveUserInfo(user);
		user = dao.findUser(user);
		return user;
	}
	
	public UserInfo changePassword(Integer uid,String password,String password2,String userAddr) {
		if(uid==null) {
			throw new UserNotFoundException("会话已过期");
		}
		if(password==null||password.trim().isEmpty()) {
			throw new RegistFaildException("密码不能为空");
		}
		password = password.trim();
		if(password2==null||password2.trim().isEmpty()||!password.equals(password2.trim())) {
			throw new RegistFaildException("确认密码错误");
		}
		int n = dao.changePwd(uid, password,userAddr);
		if(!(n==1)) {
			throw new RegistFaildException("更改密码失败");
		}
		UserInfo user = new UserInfo();
		user.setUid(uid);
		user = dao.findUser(user);
		return user;
	}

}
