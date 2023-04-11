package com.yedam.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyPageDTO;
import com.yedam.board.domain.ReplyVO;
import com.yedam.board.persistence.ReplyMapper;

@Service // Spring에서 관리하는 객체라는 뜻
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired // ReplyServivceImpl이 실행될 때 자동으로 데이터를 들고와서 만들어줌
	// setter(on method_와의 차이점. 인스턴스가 실행될 때 <- 얘가 좀 더 빠름
	private ReplyMapper replyMapper;

	@Override
	public int register(ReplyVO vo) {
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return replyMapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno, Criteria cri) {
		return replyMapper.getListWithPaging(bno, cri);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(replyMapper.getCountByBno(bno), replyMapper.getListWithPaging(bno, cri));
	}

}
