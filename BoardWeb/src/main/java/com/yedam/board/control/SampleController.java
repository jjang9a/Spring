package com.yedam.board.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.board.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value="/getText", produces = "text/plain;charset=UTF-8")
	public String getText() {
		return "안녕하세요";
	}
	
	@GetMapping(value="/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	// /getSample 로 치면 xml타입으로 나오고 /getSample.json 으로하면 제이슨타입으로 출력돼서 보임
	// 따로 선언해두지않으면 두 개 모두 가능하지만 따로 둘중 하나만 선언하면 그 방법으로만 받아올수있음. 안적힌 방법으로는 안 됨
	public SampleVO getSample() {
		SampleVO vo = new SampleVO(100, "길동", "홍");
		return vo;
	}
	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		SampleVO vo = new SampleVO(100, "길동", "홍");
		return vo;
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			list.add(new SampleVO(i+10, "firstName"+i, "lastName"+i));
		}
		return list;
	}
	
	@GetMapping("/product/{cat}/{pid}")
	// 파라메터값으로 받아와서 처리하고 싶을 때 사용
	// url 경로상의 변수 지정. product?cat=bags&pid=1001
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
													// 기본값으로는 안됨. 객체타입으로만 넘길수 있으므로 int -> Integer로 써야 함
		return new String[] { "Category : "+cat, "productId : "+pid };
	}
	
	// 이전 : 컨트롤러에 key=value 형식으로 값을 전달
	// json형식의 값을 전달하고싶으면 (@RequestBody)
	@PostMapping("/sample")
	// post방식은 주소창에 url을 입력하는 방식으로는 테스트하지못함. 크롬 확장프로그램을 통해 요청방식을 지정해서 테스트 할 것
	public SampleVO convert(@RequestBody SampleVO sample) {
		// 요청내용(payload) : {"mno":1001, "firstName":"Kildong", "lastName":"Hong"}
		return sample;
	}
	
}
