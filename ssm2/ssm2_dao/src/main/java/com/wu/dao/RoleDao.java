package com.wu.dao;

import com.wu.pojo.Permission;
import com.wu.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色持久层接口
 */
@Repository
public interface RoleDao {

    /**
     * 根据用户id查询所有用户角色
     */
    @Select("select *  from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(
            {
                    @Result(id=true,column="id",property="id"),
                    @Result(column="roleName",property="roleName"),
                    @Result(column="roleDesc",property="roleDesc"),
                    @Result(column="id",property="permissions",javaType=List.class,many=@Many(select="com.wu.dao.PermissionDao.findByRoleId"))
            })
    public List<Role> findByUserId(String userId);

    //查询所有角色信息
    @Select("select * from role")
    public List<Role> findAll()throws Exception;

    //保存角色信息
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role)throws Exception;

    //查找当前所选角色未添加的权限
    @Select("select * from permission where id not in( select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findOtherPermission(String id)throws Exception;


    //将当前所选角色和角色所选权限添加到角色权限表当中
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId)throws Exception;

    //根据角色id查询角色
    @Select("select * from role where id=#{id}")
    public Role findById(String id)throws Exception;
}
