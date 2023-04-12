package com.yedam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.domain.BookVO;
import com.yedam.persistence.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookServiceImpl implements BookService {

	@Setter(onMethod_ = @Autowired)
	private BookMapper mapper;
	
	@Override
	public int getNext() {
		log.info("서비스 - 도서 번호");
		return mapper.bookSeq() + 1;
	}

	@Override
	public void registerBook(BookVO vo) {
		log.info("서비스 - 도서 등록");
		mapper.bookInsert(vo);
	}

	@Override
	public List<BookVO> getBookList() {
		log.info("서비스 - 도서 조회");
		return mapper.getBookList();
	}

	@Override
	public List<BookVO> getRent() {
		return mapper.getRent();
	}
	
	}
