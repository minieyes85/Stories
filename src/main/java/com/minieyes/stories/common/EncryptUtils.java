package com.minieyes.stories.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	//암호화 메소드 구현, static type으로 객체 없이 메소드 사용 가능
	public static String md5(String message) {
		String encodeData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// asdf
			// [00000000, 00000000, 00000000, 00000000]
			byte[] bytes = message.getBytes();
			md.update(bytes);			
			byte[] digest = md.digest();
			
			// 숫자 -> 문자열
			for(int i = 0; i<digest.length; i++) {
				encodeData += Integer.toHexString(digest[i] & 0xff);				
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
			
		}
		
		return encodeData;
	}

}