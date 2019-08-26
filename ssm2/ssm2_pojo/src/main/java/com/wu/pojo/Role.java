package com.wu.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 角色的实体类
 */
public class Role implements Serializable{

    /*************************************私有属性***********************************/
    private String id; //主键
    private String roleName; //角色名
    private String roleDesc; //角色名
    private List<Permission> permissions; //权限资源
    private List<Users> usersList; //用户
    /*************************************set/get************************************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
