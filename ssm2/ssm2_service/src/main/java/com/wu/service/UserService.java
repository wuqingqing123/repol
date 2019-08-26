package com.wu.service;

import com.wu.dao.UserDao;
import com.wu.pojo.Role;
import com.wu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户业务层接口类
 */
public interface UserService extends UserDetailsService{

    //查询所有用户信息
    public List<Users> findAll()throws  Exception;

    //保存用户信息
    public void save(Users users)throws Exception;

    //根据id查询用户信息
    public Users findById(String id)throws Exception;

    //根据用户id查询用户所能添加的角色
    public List<Role> findOtherRole(String id)throws Exception;

    //将用户所有角色添加到指定用户当中
    public void addRoleToUser(String userId,String[]ids)throws Exception;
}
