package com.wu.service;

import com.wu.pojo.Orders;

import java.util.List;

/**
 * 订单的业务层接口类
 */
public interface OrdersService {
    //查询所有订单信息
    public List<Orders> findAll()throws Exception;

    //分页查询所有订单信息
    List<Orders> findAll (Integer page, Integer pageSize) throws Exception;

    //查询订单详细信息
    public Orders findById(String id)throws Exception;
}
