package com.yedam.service;

import com.yedam.domain.BookVO;

public interface BookService {

	public int getNext(); // 다음 도서번호
	public void registerBook(BookVO vo); // 도서 등록
}
