package com.wu.controller;

import com.github.pagehelper.PageInfo;
import com.wu.pojo.Orders;
import com.wu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单的控制类
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    /*************************************************************************/
    @Autowired
    private OrdersService ordersService;
    /*****************************************************************************/

    /**
     * 查询所有订单信息
     */
//    @RequestMapping("/findAll")
//    public ModelAndView findAll() throws  Exception{
//        ModelAndView mv=new ModelAndView();
//        //调用业务层方法
//        List<Orders> ordersList = ordersService.findAll();
//        //将获取的订单集合存入Model当中
//        mv.addObject("ordersList",ordersList);
//        //跳转到订单界面
//        mv.setViewName("orders-list");
//        return mv;
//
//    }

    /**
     * 分页查询所有订单信息
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                            Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer
                                            pageSize) throws  Exception{
        ModelAndView mv=new ModelAndView();
        //调用业务层方法
        List<Orders> ordersList = ordersService.findAll(page,pageSize);
        //通过PageInfo将分页信息存进去
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;

    }

    /**
     * 根据Id查询订单
     */
    @RequestMapping("/findById")
    @PreAuthorize("authentication.principal.username=='wqq'") //只有wqq能操作
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView mv=new ModelAndView();
        //调用业务层查询方法
        Orders orders = ordersService.findById(ordersId);
        //将查询到的订单信心存入Model
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
