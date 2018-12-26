package com.zsm.mini.dao;

import com.zsm.mini.domain.CouponBag;

public interface CouponBagMapper {
    int deleteByPrimaryKey(String fid);

    int insert(CouponBag record);

    int insertSelective(CouponBag record);

    CouponBag selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(CouponBag record);

    int updateByPrimaryKey(CouponBag record);
}