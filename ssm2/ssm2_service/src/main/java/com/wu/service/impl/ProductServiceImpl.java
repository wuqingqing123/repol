package com.wu.service.impl;
/**
 * 产品的业务层实现类
 */

import com.wu.dao.ProductDao;
import com.wu.pojo.Product;
import com.wu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    /********************************私有属性***************************************/
    @Autowired
    private ProductDao  productDao;
    /*****************************************************************************/
    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */
    public List<Product> findAll() throws Exception {
       //调用查询方法
        List<Product> products = productDao.findAll();
        return  products;
    }

    /**
     * 保存产品信息
     * @param product
     * @throws Exception
     */
    public void save(Product product) throws Exception {
        //调用持久层保存方法
        productDao.save(product);
    }
}
