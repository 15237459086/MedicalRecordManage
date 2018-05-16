package com.kurumi.util;

import java.math.BigInteger;
	

public class GuidUtil {
	/**
	 * 文件流字符串转换为的36进制值 ，不足100位前面补0
	 * @param byteStr
	 * @return
	 */
	public static String get36SystemHash(final String byteStr){
		String hexStr = Encrypt.SHA512(byteStr);
		String hashStr = new BigInteger(hexStr, 16).toString(36);
		//不足100位前面补0到100位
		if(hashStr.length() < 100) {
			hashStr = "0"+hashStr;
		}
		return hashStr;
	}
	
	/**
	 * 文件大小转换为36进制值，不足5位前面补0
	 * @param fileSize
	 * @return
	 */
	public static String get36SystemHash(final long fileSize){
		String fileSizeHashStr = new BigInteger(fileSize+"", 10).toString(36);
		while(fileSizeHashStr.length() < 5) {
			fileSizeHashStr ="0"+fileSizeHashStr;
		}
		return fileSizeHashStr;
	}
	
	public static String getRemoteUrl(final String hashStr){
		String str1 = hashStr.substring(0, 2);
		//第二季目录名称
		String str2 = hashStr.substring(2, 4);
		//第三级目录名称
		String str3 = hashStr.substring(4, 6);
		//第四级目录名称
		String str4 = hashStr.substring(6, 8);
		String remoteUrl = str1+"/"+str2+"/"+str3+"/"+str4+"/";
		return remoteUrl;
	}
	
	public static String getLocalPath(final String hashStr){
		String str1 = hashStr.substring(0, 2);
		//第二季目录名称
		String str2 = hashStr.substring(2, 4);
		//第三级目录名称
		String str3 = hashStr.substring(4, 6);
		//第四级目录名称
		String str4 = hashStr.substring(6, 8);
		String localPath = str1+"\\"+str2+"\\"+str3+"\\"+str4+"\\";
		return localPath;
	}
}
