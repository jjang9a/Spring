package com.yedam.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void getListTest() {
		Criteria cri = new Criteria(1,30);
		cri.setType("TCW");
		cri.setKeyword("user02");
		//cri.setPageNum(5);
		service.getList(cri).forEach(board -> log.info(board));
	}
//	public void getListTest() {
//		log.info(service.getList());
//	}
	
	public void removeTest() {
		log.info("결과 : "+service.remove(5L));
	}
	
	public void modifyTest() {
		BoardVO board = new BoardVO();
		board.setBno(4L);
		board.setTitle("서비스 테스트");
		board.setContent("업데이트 테스트 입니다");
		log.info("결과 : " + service.modify(board));
	}
	
	public void getTest() {
		BoardVO board = service.get(1L);
		log.info(board);
	}
	
	public void registerTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새 글 등록");
		board.setContent("서비스 테스트 글 본문입니다");
		board.setWriter("user04");
		log.info("등록 전 : " + board);
		service.register(board);
		log.info("동록 후 : " + board);
	}
	
}
