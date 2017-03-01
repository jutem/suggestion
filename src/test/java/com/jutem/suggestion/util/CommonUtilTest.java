package com.jutem.suggestion.util;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void randomString() {
		for(int i = 0; i< 10; i++) {
			int sLength = ThreadLocalRandom.current().nextInt(3, 10);
			String s = CommonUtil.RandomString(sLength);
			System.out.println(s);
		}
	}
}
