package com.wu.service.impl;

import com.wu.dao.PermissionDao;
import com.wu.pojo.Permission;
import com.wu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源权限的业务层实现类
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

    /**************************************************************************/
    @Autowired
    private PermissionDao permissionDao;
    /**************************************************************************/

    /**
     * 查询所有资源权限
     * @return
     * @throws Exception
     */
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    /**
     * 保存传入的资源权限
     * @param permission
     * @throws Exception
     */
    public void save(Permission permission) throws Exception {
        //条用持久层保存方法
        permissionDao.save(permission);

    }
}
