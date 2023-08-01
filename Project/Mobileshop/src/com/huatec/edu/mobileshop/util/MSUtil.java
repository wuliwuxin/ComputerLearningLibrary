package com.huatec.edu.mobileshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class MSUtil {
	//使用md5加密算法
	public static String md5(String msg){
		//摘要算法，将不同长度的字符串转换为等长的，不可逆
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] input=msg.getBytes();//input需要加密的信息
			byte[] output=md.digest(input);//output加密过的信息
			//将md5处理后的output结果转成字符串
			//利用Base64算法转成字符串
			String str=Base64.encodeBase64String(output);
			//String str=new String(output);//容易出现乱码，一般不使用
			return str;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("密码加密失败");
			return "";
		}
	}
}
