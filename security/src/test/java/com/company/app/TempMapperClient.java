package com.company.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.app.mapper.TempMapper;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트를 이용해서 하겠다
@ContextConfiguration(locations = "classpath:/config/*-context.xml")
public class TempMapperClient {

	@Autowired TempMapper mapper;
	
	@Test
	public void getDateTest() {
		System.out.println(mapper.getDate());
	}
	
}
