package com.morlia.service.dao;

import com.morlia.entity.UserInfo;
import com.morlia.exception.RegistFaildException;
import com.morlia.exception.UserNotFoundException;

public interface LoginService {
	
	UserInfo login(String account,String password, boolean codeState) 
			throws UserNotFoundException;
	
	UserInfo regist(String account,String password,String password2,String email,boolean codeState) 
			throws RegistFaildException;
	
	UserInfo findUserInfoByName(String account);
	
	UserInfo changePassword(Integer uid,String password,String password2,String userAddr);

}
