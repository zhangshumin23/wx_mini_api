package com.zsm.mini.dao;

import com.zsm.mini.domain.City;

public interface CityMapper {
    int deleteByPrimaryKey(String fid);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}