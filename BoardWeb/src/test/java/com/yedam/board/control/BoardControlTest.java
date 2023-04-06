package com.yedam.board.control;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.yedam.board.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration // 컨트롤 테스트용
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControlTest {

	// 처리된 결과 페이지, url 패턴 처리 : ApplicationContext
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; // url -> contril -> .jsp
	
	@Before // 테스트클래스 실행 될 때 마다 먼저 호출
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); // 인스턴스 호출
	}
	
	@Test
	public void getTest() {
		RequestBuilder rb = MockMvcRequestBuilders.get("/board/get")
				.param("bno", "4"); // param은 parameter의 값으로 String밖에 받지못하므로 문자열로 넣어줘야함
		try {
			ModelMap map = mockMvc.perform(rb)
						.andReturn()
						.getModelAndView()
						.getModelMap();
			log.info(map);
			BoardVO board = (BoardVO) map.get("board");
			log.info(board);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void removeTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/remove")
						.param("bno", "8"); // param은 parameter의 값으로 String밖에 받지못하므로 문자열로 넣어줘야함
		try {
			String vn = mockMvc.perform(rb)
						.andReturn()
						.getModelAndView()
						.getViewName(); // 맨 마지막 친구의 리턴값으로 최종 변수타입을 적으면 됨
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void modifyTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/modify")
						.param("title", "새로운 글입니다")
						.param("content", "여긴 글 내용입니다")
						.param("writer", "user07")
						.param("bno", "1"); // param은 parameter의 값으로 String밖에 받지못하므로 문자열로 넣어줘야함
		try {
			String vn = mockMvc.perform(rb)
						.andReturn()
						.getModelAndView()
						.getViewName(); // 맨 마지막 친구의 리턴값으로 최종 변수타입을 적으면 됨
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/register")
							.param("title", "새로운 글입니다")
							.param("content", "여긴 글 내용입니다")
							.param("writer", "user07");
		try {
			String vn = mockMvc.perform(rb)
						.andReturn()
						.getModelAndView()
						.getViewName(); // 맨 마지막 친구의 리턴값으로 최종 변수타입을 적으면 됨
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void listTest() {
		// /board/list
		
		try {
			// MockMvcRequestBuilders <- url을 처리해주는 클래스
			// .get("/board/list") get 방식을 이용하여 괄호안의 페이지를 호출해 받아오겠다. post방식으로 받고싶으면 .post로 하면 됨
			ModelMap map = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
							.andReturn()  // 처리결과를 받아오기 위함
							.getModelAndView()  // model에 저장된 정보를 받아옴(처리)
							.getModelMap();  // 담겨져있는 값을 map타입으로 바꿔 받겠습니다
			log.info(map);
			// 결과물의 형태 : {list=[BoardVO(bno=1, title=게시글테스트, content=게시판 등록 글 입니다, writer=user01, regdate=Thu Apr 06 00:22:38 KST 2023, updatedate=Thu Apr 06 00:22:38 KST 2023), BoardVO(bno=2, title=테스트, ...)]}
			
			List<BoardVO> list = (List<BoardVO>) map.get("list");
			for(BoardVO vo : list)
				log.info(vo);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
