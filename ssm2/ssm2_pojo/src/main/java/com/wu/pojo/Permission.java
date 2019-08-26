package com.wu.pojo;

import java.util.List;

/**
 * 资源权限的实体类
 */
public class Permission {
    /********************************私有属性***************************************/
    private String id; //主键
    private String permissionName;//权限名
    private String url; //资源路径
    private List<Role> roles; //角色
    /**********************************set/get************************************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
