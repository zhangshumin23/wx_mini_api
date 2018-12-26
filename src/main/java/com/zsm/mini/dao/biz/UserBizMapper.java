package com.zsm.mini.dao.biz;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.zsm.mini.dao.UserMapper;
import com.zsm.mini.domain.User;

@Mapper
public interface UserBizMapper extends UserMapper{

	@Select("select * from t_user")
	List<User> getAllUser();
	
	@Select("select * from t_user where openId = #{openId}")
	User selectUserByOpenId(String openId);
}
