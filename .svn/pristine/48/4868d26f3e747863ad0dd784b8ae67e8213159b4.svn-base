package com.zsm.mini.controller;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsm.mini.dao.biz.UserBizMapper;
import com.zsm.mini.domain.User;
import com.zsm.mini.dto.ResultDto;
import com.zsm.mini.service.itf.IUserService;
import com.zsm.mini.util.StringUtils;
import com.zsm.mini.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserBizMapper userBizMapper;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;
	
	@ResponseBody
    @RequestMapping(value = "/query")
    public ResultDto getUserInfo(String openId) {
		ResultDto dto = new ResultDto();
		
		User user = userBizMapper.selectUserByOpenId(openId);
		UserVo vo = new UserVo();
		if(user != null) {
			BeanUtils.copyProperties(user, vo);
		}
		dto.setSuccess(true);
		dto.setUserObject(vo);
		return dto;
	}
	@ResponseBody
	@RequestMapping(value = "/get/user/phone")
	public ResultDto getUserPhone(String openId) {
		ResultDto dto = new ResultDto();
		User user = userBizMapper.selectUserByOpenId(openId);
		if(user != null && !StringUtils.isEmptyString(user.getPhone())) {
			dto.setSuccess(true);
		}else {
			dto.setSuccess(false);
		}
		return dto;
		
	}
	
}
