package com.zsm.mini.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsm.mini.dao.biz.UserBizMapper;
import com.zsm.mini.domain.User;
import com.zsm.mini.service.itf.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserBizMapper userBizMapper;
	
}
