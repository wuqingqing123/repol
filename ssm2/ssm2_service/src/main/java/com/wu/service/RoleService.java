package com.wu.service;

import com.wu.pojo.Permission;
import com.wu.pojo.Role;

import java.util.List;

/**
 * 角色业务层接口
 */
public interface RoleService {
    //查询所有角色信息
    public List<Role> findAll()throws Exception;

    //保存角色信息
    public void save(Role role)throws Exception;

    //根据角色id查询角色所能添加的权限
    public List<Permission> findOtherPermission(String id)throws Exception;

    //将角色所选权限添加到指定角色当中
    public void addPermissionToRole(String roleId,String[]ids)throws Exception;

    //根据id查询角色
    public Role findById(String id)throws  Exception;
}
