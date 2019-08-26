package com.wu.controller;

import com.wu.pojo.Permission;
import com.wu.pojo.Role;
import com.wu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 角色的控制类
 */
@RequestMapping("/role")
@Controller
public class RoleController {
    /****************************************私有属性********************************************/
    @Autowired
    private RoleService roleService;
    /*********************************************************************************/

    /**
     * 查询所有角色信息
     */
    @RequestMapping("/findAll")

    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        //调用业务层查询方法
        List<Role> roles = roleService.findAll();
        //将查询到的用户信息存储到Model当中
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 保存角色信息
     */
    @RequestMapping("/save")
    @Secured("ROLE_ADMIN")   //不能省略前缀
    public String save(Role role) throws Exception {
        //调用业务层保存方法
        roleService.save(role);
        //重定向到查询所有角色信息界面
        return "redirect:findAll";

    }

    /**
     * 根据用户id查找用户和该用户可以添加的角色
     */
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name="id",required = true) String roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        //根据用户id查询用户
        Role role= roleService.findById(roleId);
        //根据用户查询可以添加的角色
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        //将查询到的用户和角色添加到Model当中
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        //跳转到用户角色添加界面
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 将当前选择用户所选角色添加到用户角色表当中
     */
    @RequestMapping("/addPermissionToRole")
    public String addRoleToUser(@RequestParam(name="roleId",required = true)String roleId,@RequestParam(name="ids",required = true)String[] ids)throws Exception{
        //调用业务层方法将角色添加到所选用户当中
        roleService.addPermissionToRole(roleId,ids);

        return "redirect:findAll";
    }
}
