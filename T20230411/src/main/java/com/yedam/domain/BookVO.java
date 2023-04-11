package com.yedam.domain;

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
}
