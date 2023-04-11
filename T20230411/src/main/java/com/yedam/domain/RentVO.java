package com.yedam.domain;

import lombok.Data;

@Data
public class RentVO {
	
	private int rentNo;
	private int bookNo;
	private int rentPrice;
	private String rentDate;
	private String rentStatus;
}
