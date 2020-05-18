package com.zq.common.encryption;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密类
 * 
 * @author tgh
 *
 */
public class Encryption {

	private static Blowfish blowfish = new Blowfish("ZGNvdHJqb2I6dGFuZ2do");

	/**
	 * @Title: encode
	 * @Description: 编码
	 * @author zhuzq
	 * @date 2020年5月18日 下午2:01:10
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		if (null == s || "".equals(s.trim())) {
			return null;
		}
		String p1 = blowfish.encryptString(s);
		byte[] ps = Base64.encodeBase64(p1.getBytes());
		return new String(ps);
	}

	/**
	 * @Title: decode
	 * @Description: 解码
	 * @author zhuzq
	 * @date 2020年5月18日 下午2:01:17
	 * @param s
	 * @return
	 */
	public static String decode(String s) {
		if (null == s || "".equals(s.trim())) {
			return null;
		}
		byte[] p1 = Base64.decodeBase64(s.getBytes());
		String p2 = blowfish.decryptString(new String(p1));
		return p2;
	}
	
	public static void main(String[] args) {
		System.out.println(encode("17673053518"));
		System.out.println(encode("hly8784106"));
	}
}
