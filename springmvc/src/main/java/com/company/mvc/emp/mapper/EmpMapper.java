package com.company.mvc.emp.mapper;

import java.util.List;
import java.util.Map;

import com.company.mvc.emp.EmpVO;

public interface EmpMapper {

	List<DeptVO> getDeptList(); // 부서 전체 목록
	int deptDelete(DeptVO vo); // foreach를 이용해 여러건 한번에 삭제
	List<EmpVO> getEmpListVO(EmpVO vo);
	List<Map<String, Object>> getEmpList();
	Map<String, Object> getEmp(int value);
	
}
