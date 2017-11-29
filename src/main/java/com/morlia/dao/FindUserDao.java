package com.morlia.dao;

import org.apache.ibatis.annotations.Param;

import com.morlia.entity.UserInfo;

public interface FindUserDao {
	
	UserInfo findUser(@Param("user") UserInfo user);
	
	int updateAccessed(int uid);
	
	int saveUserInfo(@Param("user") UserInfo user);
	
	int changePwd(@Param("uid") int uid,@Param("password") String password,@Param("userAddr") String userAddr);

}
