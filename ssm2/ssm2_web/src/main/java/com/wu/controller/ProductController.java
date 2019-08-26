package com.wu.controller;


import com.wu.pojo.Product;
import com.wu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 *产品的视图层控制类
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    /*************************************************************/
    @Autowired
    private ProductService productService;
    /****************************************************************/

    /**
     * 查询所有
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception{
        //创建ModelAndView对象
        ModelAndView mv=new ModelAndView();
        //调用业务层方法
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product-list1");
        return mv;

    }

    /**
     * 产品添加
     */
     @RequestMapping("/save")
    public String save(Product product) throws Exception {
         //调用业务层保存方法
         productService.save(product);
         System.out.println(product.getDepartureTime());
         //重定向到查询方法
         return "redirect:findAll";
     }
}
