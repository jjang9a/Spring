package com.yedam.board.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.board.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTest {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	//@Test
	public void updateTest() {
		BoardVO board = new BoardVO();
		board.setBno(2L);
		board.setTitle("테스트");
		board.setContent("업데이트 테스트 입니다");
		log.info(mapper.update(board) ==1 ? "성공" : "실패");
	}
	
	public void deleteTest() {
		log.info(mapper.delete(3L) ==1 ? "성공" : "실패");
	}
	
	public void readTest() {
		BoardVO board = mapper.read(3L); // 매개값이 Long타입이므로 그냥 3으로 안쓰고 3L로 써야함
		log.info(board);
	}
	
	public void insertTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새 글 등록");
		board.setContent("글 본문입니다");
		board.setWriter("user03");
		mapper.insertSelectKey(board);
		
	}
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
}
