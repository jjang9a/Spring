package com.company.mvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.mvc.emp.EmpVO;
import com.company.mvc.emp.mapper.DeptVO;
import com.company.mvc.emp.mapper.EmpDAO;
import com.company.mvc.emp.mapper.EmpMapper;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트를 이용해서 하겠다
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmpMapperClient {

	@Autowired EmpMapper empMapper;
	@Autowired EmpDAO dao;
	
	@Test
	public void getDeptList() {
		List<DeptVO> list = empMapper.getDeptList();
		for(DeptVO dept : list) {
			System.out.println("부서 : "+dept.getDepartmentName());
			for(EmpVO emp : dept.getEmps()) {
				System.out.println("\t"+emp.getEmployeeId()+"_"+emp.getFirstName());
			}
		}
	}
	
	public void deptDelete() {
		DeptVO vo = new DeptVO();
		vo.setDepartmentIds(Arrays.asList("300", "310"));
		// Arrays.asList 반환타입 : 배열
		empMapper.deptDelete(vo);
	}
	
	public void 사원전체조회VO() {
		EmpVO vo = new EmpVO();
		vo.setDepartmentId("50");
		// ㄴ 이 조건을 주석처리하면(departmentId)값 설정을 안해주면 전체 뜨고 이 값이 있으면 whrer걸려서 조회 됨
		//vo.setFirstName("vin");
		List<EmpVO> list = empMapper.getEmpListVO(vo);
		System.out.println(list.get(0));
	}
	
	public void DAO사원전체조회() {
		List<Map<String,Object>> list = dao.getEmpList();
		System.out.println(list.get(0));
		System.out.println("First_name=" + list.get(0).get("firstName"));
		// first_name만 가져오고싶을 때 사용. 이때는 카멜케이스 적용안되므로 SQL에 뜨는 그대로 대문자로 적어주거나(FIRST_NAME) 별칭지정
	}
	
	public void 사원전체조회() {
		List<Map<String,Object>> list = empMapper.getEmpList();
		System.out.println(list.get(0));
	}
}
