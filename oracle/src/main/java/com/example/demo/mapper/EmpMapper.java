package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.emp.DeptVO;
import com.example.demo.emp.EmpVO;

public interface EmpMapper {

	List<DeptVO> getDeptList(); // 부서 전체 목록
	int deptDelete(DeptVO vo); // foreach를 이용해 여러건 한번에 삭제
	List<EmpVO> getEmpListVO(EmpVO vo);
	List<Map<String, Object>> getEmpList();
	Map<String, Object> getEmp(int value);
	
}
