package com.company.mvc;

import org.junit.Test;

import com.company.mvc.emp.mapper.DeptVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperClient {

	// string 타입의 자바객체를 json타입으로 변환
	public void write() throws JsonProcessingException {
		DeptVO vo = new DeptVO();
		vo.setDepartmentId("10");
		vo.setDepartmentName("개발부");
		
		ObjectMapper om = new ObjectMapper();
		String result = om.writeValueAsString(vo);
		System.out.println(result);
	}
	
	@Test
	// json타입의 문자열을 자바객체로 변환
	public void read() throws JsonMappingException, JsonProcessingException {
		String sourde = "{\"departmentId\":\"10\",\"departmentName\":\"개발부\",\"managerId\":null,\"locationId\":null}";
		ObjectMapper om = new ObjectMapper();
		DeptVO vo = om.readValue(sourde, DeptVO.class);
		System.out.println(vo.getDepartmentName());
	}
	
}
