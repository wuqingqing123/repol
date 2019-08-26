package com.wu.controller;

import com.wu.pojo.Role;
import com.wu.pojo.Users;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户的控制类
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /*******************************************************************/
    @Autowired
    private  UserService userService;
    /******************************************************************/

    /**
     * 查询所有用户信息
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        //调用业务层查询方法
        List<Users> usersList = userService.findAll();
        //存储用户信息到Model当中
        mv.addObject("userList",usersList);
        //跳转
        mv.setViewName("user-list");

        return mv;
    }

    /**
     * 保存用户信息
     */
    @RequestMapping("/save")
    public String save(Users users) throws Exception {
        //调用业务层保存用户信息的方法
        userService.save(users);
        return "redirect:findAll";
    }

    /**
     * 根据用户id查询用户
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="id",required = true) String id)throws  Exception{
        //调用业务层查询方法
        Users users=userService.findById(id);
        ModelAndView mv=new ModelAndView();
        //储存查询到的用户信息存储到Model当中
        mv.addObject("user",users);
        //跳转到用户信息展示界面
        mv.setViewName("user-show1");
        return  mv;
    }

    /**
     * 根据用户id查找用户和该用户可以添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true) String userId)throws Exception{
        ModelAndView mv=new ModelAndView();
        //根据用户id查询用户
        Users users = userService.findById(userId);
        //根据用户查询可以添加的角色
        List<Role> roleList = userService.findOtherRole(userId);
        //将查询到的用户和角色添加到Model当中
        mv.addObject("user",users);
        mv.addObject("roleList",roleList);
        //跳转到用户角色添加界面
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 将当前选择用户所选角色添加到用户角色表当中
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name="userId",required = true)String userId,@RequestParam(name="ids",required = true)String[] ids)throws Exception{
       //调用业务层方法将角色添加到所选用户当中
        userService.addRoleToUser(userId,ids);

        return "redirect:findAll";
    }
}
