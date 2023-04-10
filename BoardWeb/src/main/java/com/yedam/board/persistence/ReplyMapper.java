package com.yedam.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);
	public ReplyVO read(Long rno); // 댓글번호 조회
	public int delete(Long rno); // 댓글 삭제
	public int update(ReplyVO vo); // 수정
	public List<ReplyVO> getListWithPaging(@Param("bno") Long bno, @Param("cri") Criteria cri); // 글번호(페이지, 건수)
	// Param지정해야 하는 이유. 보통 Mapper.xml안에는 parameterType을 하나만 넣었음. 근데 이번에는 두개 넣었으니까 그것을 구분해주기 위해서 이름을 써 주어야 함
	public int getCountByBno(Long bno); // 전체건수
	
	
}