package com.jutem.suggestion.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 通用工具
 */
public class CommonUtil {
	
	//a
	private static final int LOW_ASCII = 97;
	//z
	private static final int HIGH_ASCII = 122;

	public static String RandomString(int length) {
		if(length <= 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++) {
			int ascii = ThreadLocalRandom.current().nextInt(LOW_ASCII, HIGH_ASCII);
			sb.append((char)ascii);
		}
		return sb.toString();
	}
	
}
