package com.yedam.persistence;

import java.util.List;

import com.yedam.domain.BookVO;

public interface BookMapper {
	
	public int bookSeq(); // 다음 도서번호
	public int bookInsert(BookVO vo); // 도서 등록
	public List<BookVO> getBookList(); // 도서 목록 출력
	public List<BookVO> getRent(); // 대출 정보 출력

}
