package com.company.mvc.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.mvc.emp.mapper.DeptVO;
import com.company.mvc.emp.mapper.EmpMapper;

@RestController // 모든 핸들러가 (굳이 적지않아도) @ResponseBoay를 포함하고 있음
public class EmpController {

	@Autowired
	EmpMapper empMapper;
	
	@GetMapping("/getDept")
	public List<DeptVO> getDept() {
		return empMapper.getDeptList();
	}
	
	@PostMapping("/deptInsert")
	public DeptVO deptInsert(@RequestBody DeptVO vo) {
		//@RequestBody를 넣게 되면 반드시 json 형태로만 입력 가능
		return vo;
	}
	
}
