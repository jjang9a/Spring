package com.example.demo.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.mapper.EmpMapper;

@Controller
public class EmpController {
	
	@Autowired
	EmpMapper empMapper;

	@RequestMapping("/empList")
	public String empList(Model model){
		model.addAttribute("empList", empMapper.getEmpListVO(null));
		return "empList"; 
	}
}
