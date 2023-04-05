package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	public SampleController() {
		log.info("SampleController() call.");
	}
	
	// jsp기준 : command 구현 클래스
	
	@RequestMapping("page1")
	public String basic() {
		// /sample/page1 호출. 해당 메소드 실행.
		log.info("basic...");
		return "page1"; // /WEB-INF/views/page1 + .jsp
	}
	
	@RequestMapping("page2")
	public void basic2() {
		log.info("basic2...");
		// 반환값이 없음(리턴값을 따로 안 적으면) : /sample/page2 => /WEB-INF/views/sample/page2.jsp
	}
	
	@RequestMapping("myInfo")
	public void myInfo() {
		log.info("myInfo지롱");
	}
	
	@RequestMapping(value="page3", method= { RequestMethod.GET, RequestMethod.POST })
	public String basic3() {
		return "page3";
	}
	
	@GetMapping("page4")
	public String basic4() {
		return "page4";
	}
	
	@PostMapping("page5")
	public String basic5() {
		return "page5";
	}
	
	@GetMapping("ex01")
	public String ex01(@RequestParam("name") String name) {
		log.info("name=>" + name);
		return "ex01";
	}
	
	@GetMapping("ex02")
	public String ex02(SampleDTO dto, Model model, int score) {
		log.info(dto);
		model.addAttribute("name", dto.getName()); // request.setAttribute("name", dto.getName());
		model.addAttribute("score", score); // 참조값을 담아서 반환 int
		return "ex02";
	}
	
	@GetMapping("ex03")
	public String ex03(@RequestParam("ids") ArrayList<String> ids) {
		for(String id : ids) {
			log.info(id);
		}
		return "ex02";
	}
	
	// ★★★ Jackson databind 활용
	@GetMapping("ex04")
	public @ResponseBody SampleDTO ex04() {
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("Hong"); // 앞에 responserBody 붙여주면 내부적으로 json 생성
		return dto;
	}
}
