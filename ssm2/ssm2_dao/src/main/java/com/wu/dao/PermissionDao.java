package com.wu.dao;

import com.wu.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源权限的持久层藉口1
 */
@Repository
public interface PermissionDao {
    /**
     * 根据角色id查询资源权限
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId(String roleId)throws Exception;

    //查询所有资源权限
    @Select("select * from permission")
    public List<Permission> findAll()throws Exception;

    //保存新的资源权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission)throws Exception;
}
