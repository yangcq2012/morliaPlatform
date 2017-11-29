package com.morlia.util;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
/*
 * 生成appkey
 */
public class TokenUtil {
	
	private static final char[] CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static String getToken(int uid) {
		
		return null;
	}
	
	public static String createToken(int len) {
		len = len>0?len:16;
		String token = "";
		for(int i=0;i<CHARS.length;i++) {
			int index = new Random().nextInt(CHARS.length);
			char c = CHARS[index];
			token +=c;
		}
		//md5加密
		//System.out.println("pre,"+token);
		token = DigestUtils.md5Hex(token);
		return token;
	}
	
	public static String createToken() {
		int len = 16;
		return createToken(len);
	}
	/*
	 * main方法测试
	 */
	public static void main(String[] args) {
		String token = createToken();
		System.out.println(token);
	}

}
