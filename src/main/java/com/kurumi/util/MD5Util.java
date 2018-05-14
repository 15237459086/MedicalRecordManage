package com.kurumi.util;

import java.security.MessageDigest;
/**
 * MD5 util
 * @author lyh
 *
 */
public class MD5Util {

	/**
	 * 依据字符串获取MD5后的字符串
	 * @param str
	 * @return md5
	 */
	public static String getMD5(String str) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			md5 = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return md5;
	}
	
	public static void main(String[] args) {
		System.out.print(getMD5("adming"));
	}
}
