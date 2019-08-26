package com.wu.service.impl;

import com.wu.dao.RoleDao;
import com.wu.pojo.Permission;
import com.wu.pojo.Role;
import com.wu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    /**********************************************************************/
    @Autowired
    private RoleDao roleDao;
    /************************************************************************/

    /**
     * 查询角色信息
     * @return
     * @throws Exception
     */
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 保存角色信息
     * @param role
     * @throws Exception
     */
    public void save(Role role) throws Exception {
        //调用持久层保存方法
        roleDao.save(role);
    }

    /**
     * 查找角色所能添加的权限
     * @param id
     * @return
     * @throws Exception
     */
    public List<Permission> findOtherPermission(String id) throws Exception {
        return roleDao.findOtherPermission(id);
    }

    /**
     * 将所选权限添加到指定角色当中
     * @param roleId
     * @param ids
     * @throws Exception
     */
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        //遍历权限数组
        for (String id : ids) {
            //调用持久层方法将权限添加到指定角色当中
            roleDao.addPermissionToRole(roleId,id);
        }

    }

    /**
     * 根据角色id查询角色
     * @param id
     * @return
     * @throws Exception
     */
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }


}
