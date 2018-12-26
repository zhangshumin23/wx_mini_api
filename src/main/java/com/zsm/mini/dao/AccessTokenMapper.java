package com.zsm.mini.dao;

import com.zsm.mini.domain.AccessToken;

public interface AccessTokenMapper {
    int deleteByPrimaryKey(String fid);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);

    AccessToken selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
}