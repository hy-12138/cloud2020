package com.hy.springCloud.dao;

import com.hy.springCloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentDao {
    //插入数据
    public int create(Payment payment);
    //根据id查询数据
    public Payment selectById(@Param("id") Integer id);
}
