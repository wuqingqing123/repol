package com.wu.service;

import com.wu.pojo.Permission;

import java.util.List;

/**
 * 资源权限的业务层接口
 */
public interface PermissionService {
    //查询所有资源权限
    public List<Permission> findAll()throws Exception;

    //保存资源权限
    public void save(Permission permission) throws Exception;
}
