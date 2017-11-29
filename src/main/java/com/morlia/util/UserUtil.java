package com.morlia.util;

import java.util.regex.Pattern;

public class UserUtil {
	
	public static boolean isEmail(String username) {
		String pattern = "([0-9A-Za-z\\.]+)@([0-9a-z]+\\.[a-z]{2,3}(\\.[a-z]{2})?)";
		return Pattern.matches(pattern,username);
	}
	
	public static boolean isDigitsString(String username) {
		String pattern = "\\d*";
		return Pattern.matches(pattern, username);
	}
	
	public static boolean isUsername(String username) {
		String pattern = "([a-zA-Z_])([\\w_]{5,15})";
		return Pattern.matches(pattern, username);
	}
	
	public static String getOs(String device) {
		return device.contains("-")?"ios":"android";
	}
	
	public static void main(String[] args) {
		String name = "476853585@qq.com.cn";
		System.out.println(UserUtil.isEmail(name));
		System.out.println(getOs("68C07B2D-F868-46EA-887F-2404DFEF8EA1"));
	}

}
