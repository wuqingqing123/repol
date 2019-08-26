package com.wu.service;

import com.wu.pojo.Product;

import java.util.List;

/**
 * 产品的业务层接口
 */
public interface ProductService {

    //查询所有产品信息
    public List<Product> findAll() throws Exception;

    //保存产品信息
    void save(Product product)throws Exception;
}
