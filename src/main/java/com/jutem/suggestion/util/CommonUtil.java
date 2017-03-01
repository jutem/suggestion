package com.jutem.suggestion.util;

import java.util.concurrent.ThreadLocalRandom;

public class CommonUtil {
	
	private static final int LOW_ASCII = 97;
	private static final int HIGH_ASCII = 122;
	
	public static String RandomString(int length) {
		if(length <= 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++) {
			sb.append((char)(ThreadLocalRandom.current().nextInt(LOW_ASCII, HIGH_ASCII)));
		}
		return sb.toString();
	}
	
}
