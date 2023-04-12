package com.company.mvc.emp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
// mapper interface를 대신하여 사용 가능
// ㄴ 얘가 mybatis기능을 이용하여 이 작업을 대신해준다고 생각하면 됨
public class EmpDAO {

	@Autowired
	SqlSession mybatis;
	
	public List<Map<String, Object>> getEmpList(){
		return mybatis.selectList("com.company.mvc.emp.mapper.EmpMapper.getEmpList");
	}
	
	public Map<String, Object> getEmp(int value){
		return mybatis.selectOne("com.company.mvc.emp.mapper.EmpMapper.getEmp");
	}
	
}
