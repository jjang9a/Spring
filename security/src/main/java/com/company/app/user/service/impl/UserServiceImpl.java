package com.company.app.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.app.user.UserVO;
import com.company.app.user.mapper.UserMapper;
import com.company.app.user.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired UserMapper userMapper;
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userMapper.getUser(vo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 단건조회
			UserVO userVO =new UserVO();
			userVO.setLoginId(username);
			UserVO vo = userMapper.getUser(userVO);
		// ID가 없으면 error
			if(vo == null) {
				throw new UsernameNotFoundException("no id");
			}
		// ID가 있다면 VO return
			return vo;
	}

}
