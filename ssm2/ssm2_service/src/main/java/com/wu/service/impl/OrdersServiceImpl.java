package com.wu.service.impl;

import com.github.pagehelper.PageHelper;
import com.wu.dao.OrdersDao;
import com.wu.pojo.Orders;
import com.wu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单的业务层实现类
 */
@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService{
    /***************************私有属性***************************/
    @Autowired
    private OrdersDao ordersDao;


    /*******************************************************/

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Orders> findAll() throws Exception {
        //调用持久层方法
        List<Orders> list = ordersDao.findAll();
        return list;
    }

    /**
     * 分页查询所有
     */
    public List<Orders> findAll(Integer page, Integer pageSize) throws Exception {
        //设定分页和分页的起始页page和每页的数据条数pageSize
        PageHelper.startPage(page,pageSize);
        //调用持久层方法
        List<Orders> list = ordersDao.findAll();
        return list;
    }

    /**
     * 查看订单详情
     */
    public Orders findById(String id)throws Exception{
        //调用持久层查询方法
        Orders orders = ordersDao.findById(id);
        return orders;
    }
}
