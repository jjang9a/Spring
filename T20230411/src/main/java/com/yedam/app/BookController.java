package com.yedam.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yedam.domain.BookVO;
import com.yedam.service.BookService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BookController {
	
	@Setter(onMethod_ = @Autowired)
	private BookService bookServie;

	@GetMapping("/index")
	public void index(){
		log.info("index page");
	}
	
	@GetMapping("/add")
	public void add(Model model) {
		log.info("add Form...");
		model.addAttribute("bno", bookServie.getNext());
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(BookVO vo) {
		log.info("컨트롤 - 도서 등록");
		bookServie.registerBook(vo);
		
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("컨트롤 - 도서 조회");
		List<BookVO> list = bookServie.getBookList();
		log.info(list);
		model.addAttribute("list", list);
	}
	
	@GetMapping("/rent")
	public void rent(Model model) {
		log.info("컨트롤 - 대여 조회");
		List<BookVO> list = bookServie.getRent();
		log.info(list);
		model.addAttribute("list", list);
	}
	
}
