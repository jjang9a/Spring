package com.yedam.board.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage; // (11 startPage) ... (15 현재) ... (20 endPage)
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		// 3page / 10 = 0.3 => 1*10 = 10
		this.endPage = (int) (Math.ceil(cri.getPageNum()/10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int) (Math.ceil(total*1.0)/cri.getAmount());
		if( realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
