package com.yedam.persistence;

import com.yedam.domain.BookVO;

public interface BookMapper {
	
	public int bookSeq(); // 다음 도서번호
	public int bookInsert(BookVO vo); // 도서 등록

}
