package com.wu.controller;

import com.wu.pojo.Permission;
import com.wu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 资源权限的试图层控制类
 */
@RequestMapping("/permission")
@Controller
public class PermissionController {

    /****************************************************************************/
    @Autowired
    private PermissionService permissionService;
    /*****************************************************************************/


    /**
     * 查询所有资源权限
     */
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")  //只有ADMIN角色可以使用该方法,该注解可以省略ROLE_
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        //调用业务层查询方法
        List<Permission> permissionList = permissionService.findAll();
        //将查询到的资源权限信息放入Model当中
        mv.addObject("permissionList",permissionList);
        //跳转到资源权限展示界面
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 保存资源权限
     */
    @RequestMapping("/save")
    public String save(Permission permission) throws Exception{
        //调用业务层保存方法
        permissionService.save(permission);
        //重定向到查询界面
        return "redirect:findAll";

    }
}
