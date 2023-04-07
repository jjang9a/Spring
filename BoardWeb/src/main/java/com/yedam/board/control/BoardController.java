package com.yedam.board.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.PageDTO;
import com.yedam.board.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	@Setter(onMethod_ = @Autowired) // 인스턴스가 실행될때 자동으로 메소드를 담아주겠습니다
	private BoardService boardService;
	
	@RequestMapping("list") // 위에서 매핑 해놨기 때문에 board/밑의 이름만 써주면 됨
	public void list(Model model, Criteria cri) { // model을 하는 이유 컨트롤 -> jsp로 값을 넘겨주고 싶음
		log.info("컨트롤 ... 목록조회"); // ㄴ page = 페이지 정보, amount 한 페이지당 담아올 양 -> 도메인에 클래스 선언
		//List<BoardVO> list =  boardService.getList(); // 페이징 따로X(CSS기본설정)
		List<BoardVO> list =  boardService.getList(cri); // 페이징 되게 수정
		int total = boardService.getTotalCount(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		// /WEB-INF/views/board/list.jsp
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("컨트롤 ... 등록");
		// 등록 처리 후 목록 이동 (실제 등록 처리 기능)
		boardService.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		// 일회성으로 한번만 받아오고 뒤로가기했다가 다시 오면 안떴으면 좋겠을때 Flash사용
		// 계속 떠있었으면 좋겠다면 그냥 addAttribute
		
		return "redirect:/board/list"; // response.sendRedirect();
	}
	
	@GetMapping("register")
	public void register() {
		// 등록 화면 열어주는 기능
	}
	
	@GetMapping({"get", "modify"}) // 같은 기능이 필요하므로 추가. get(단건조회)페이지를 요청하거나 modify를 요청하면 이 메소드 실행 
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
							// ㄴ 파라미터로 넘어온 값을 Model값으로 바로 쓰고 싶다면 밑에 model.~ 한 것과 같은 효과
		log.info("컨트롤 ... 단건조회 / 수정이동");
		model.addAttribute("board", boardService.get(bno));	
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, Criteria cri ,RedirectAttributes rttr) {
		log.info("컨트롤 ... 수정");
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result", "Success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list"; // response.sendRedirect();
		// forwarding이 아닐 경우에는 Model이 아니라 RedirectAttributes를 사용해야 한다
	}
	
	@PostMapping("remove")
	public String remove(Long bno, Criteria cri ,RedirectAttributes rttr) {
		log.info("컨트롤 ... 삭제");
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "Success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list"; // response.sendRedirect();
	}
}