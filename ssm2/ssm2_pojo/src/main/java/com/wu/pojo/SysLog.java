package com.wu.pojo;

import com.wu.utils.DateUtils;

import java.util.Date;

/**
 * 日志的处理类
 */
public class SysLog {
    /**************************************私有属性**************************************/
    private String id; //id
    private Date visitTime; //访问时间
    private String visitTimeStr;
    private String username; //操作者用户名
    private String ip; //访问Ip
    private String url; //访问资源
    private Long executionTime; //执行时长
    private String method; //访问方法
    /************************************set/get*****************************************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        return DateUtils.dateToString(visitTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
