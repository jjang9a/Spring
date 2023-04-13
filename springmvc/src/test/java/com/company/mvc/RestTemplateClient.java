package com.company.mvc;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class RestTemplateClient {

	@Test
	public void restTest() {
		RestTemplate rest = new RestTemplate();
		String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20230412";
		JsonNode result = rest.getForObject(url, JsonNode.class);
		//String result = rest.getForObject(url, String.class);
		System.out.println(result.get("boxOfficeResult").get("dailyBoxOfficeList").get(0).get("movieNm"));
		// 파일을 열어보면 중괄호안에 중괄호안에.... 형태로 생김 중괄호 하나마다 하나의 노드라고 생각하면 됨
		// 안쪽에 있는 특정값을 받아오고싶다면 get.("노드이름").("노드이름")으로 들어가 배열형태일 시 그 안의 배열에서 인덱스를 사용해서 찾고 .get(0)
		// 그 안에서 특정 항목만 받아오고싶다면 .get("항목이름")해서 받아오면 됨
	}

	
}
