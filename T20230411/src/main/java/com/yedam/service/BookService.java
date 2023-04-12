package com.yedam.service;

import java.util.List;

import com.yedam.domain.BookVO;

public interface BookService {

	public int getNext(); // 다음 도서번호
	public void registerBook(BookVO vo); // 도서 등록
	public List<BookVO> getBookList(); // 도서 목록
	public List<BookVO> getRent(); 
}
