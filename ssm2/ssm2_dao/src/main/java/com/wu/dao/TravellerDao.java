package com.wu.dao;

import com.wu.pojo.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 旅客的持久层接口
 */
@Repository
public interface TravellerDao {

    /**
     * 查询所有旅客信息
     */
    @Select("select *  from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId)throws Exception;
}
