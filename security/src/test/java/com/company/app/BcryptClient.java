package com.company.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptClient {

	@Test
	public void encode() {
		BCryptPasswordEncoder bcrype = new BCryptPasswordEncoder(10);
											// 괄호 안의 숫자 : 강도 - 보안상의 이유로 시간을 지연시킴. 숫자가 작을수록 빨라짐
		String password = bcrype.encode("1111");
		System.out.println(password);
		assertTrue(bcrype.matches("1111", password));
	}
}
