package com.wu.dao;

import com.wu.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品的持久层接口
 */
@Repository
public interface ProductDao {
    //查询所有的产品信息
    @Select("select * from product")
    public List<Product> findAll()throws Exception;

    //保存产品信息
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            " values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})  ")
    void save(Product product)throws Exception;

    //通过id查询
    @Select("select * from product where id=#{id}")
    Product findById(String id)throws  Exception;
}
