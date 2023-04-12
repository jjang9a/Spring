package com.yedam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {

	private int bookNo;
	private String bookName;
	private String bookCoverimg;
	private String bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
	
	private int rentNo;
	private int rentSum;
	private int rentCnt;
}
