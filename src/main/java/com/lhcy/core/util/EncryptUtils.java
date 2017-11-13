package com.lhcy.core.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

@SuppressWarnings("restriction")
public class EncryptUtils {
	
	public static String getMd5String(String str){

        if (str != null && str.length() == 0){
            return "";
        }

		String result = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			result = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return result;
	}
}
