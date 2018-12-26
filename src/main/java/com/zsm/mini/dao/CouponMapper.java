package com.zsm.mini.dao;

import com.zsm.mini.domain.Coupon;

public interface CouponMapper {
    int deleteByPrimaryKey(String fid);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}