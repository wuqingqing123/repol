package com.wu.dao;

import com.wu.pojo.Role;
import com.wu.pojo.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户的持久层接口
 */
@Repository
public interface UserDao {
    //查询所有用户信息
    @Select("select * from users")
    public List<Users> findAll()throws  Exception;

    //根据姓名查找用户
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select="com.wu.dao.RoleDao.findByUserId"))

    })
    public Users findByUsername(String username)throws Exception;

    //根据用户id查询所有用户
    @Select("select * from users where id=#{id}")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.wu.dao.RoleDao.findByUserId")) })
    public Users findById(String id) throws Exception;

    //保存用户信息
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(Users users)throws Exception;

    //查找当前所选用户未添加的角色
    @Select("select * from role where id not in( select roleId from users_role where userId=#{id})")
    public List<Role> findOtherRole(String id)throws Exception;


    //将当前所有选用户和用户所选角色添加到用户角色表当中
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId)throws Exception;
}
