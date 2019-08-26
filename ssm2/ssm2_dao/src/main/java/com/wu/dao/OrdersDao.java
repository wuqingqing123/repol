package com.wu.dao;

import com.wu.pojo.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单的持久层接口
 */
@Repository
public interface OrdersDao {

    /**
     * 查询所有
     * 配置订单实体类与数据库的映射
     */
    @Select("select * from orders")
    @Results(value={
            @Result(id = true, column = "id", property = "id"),

            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", one = @One(select =
                    "com.wu.dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results(value={
            @Result(id = true, column = "id", property = "id"),

            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", one = @One(select =
                    "com.wu.dao.ProductDao.findById")),
            @Result(column = "memberId", property = "member", one = @One(select =
                    "com.wu.dao.MemberDao.findById")),
            @Result(column = "id", property = "travellers", many = @Many(select =
                    "com.wu.dao.TravellerDao.findByOrdersId"))

    })
    //根据id查询一个
    public Orders findById(String id)throws  Exception;
}
