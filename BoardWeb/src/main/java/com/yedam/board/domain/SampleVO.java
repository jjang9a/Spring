package com.yedam.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 기본생성자 외에 필요한 값을 다 매개값으로 넣는 생성자를 만들어 줌
@NoArgsConstructor // 매개값이 없는 기본생성자를 만들어 줌
// 원래 Data 자체에 기본생성자가 포함되어있었지만 따로 매개값을 받는 생성자를 만들면 기본생성자가 안 만들어지므로 따로 생성해주어야함
public class SampleVO {
	private int mno;
	private String firstName;
	private String lastName;
}
