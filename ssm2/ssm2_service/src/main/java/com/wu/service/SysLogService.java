package com.wu.service;

import com.wu.pojo.SysLog;

import java.util.List;

/**
 * 日志的业务层接口
 */

public interface SysLogService {
    //保存日志信息
    public void save(SysLog log)throws Exception;

    //查询所有日志信息
    public List<SysLog> findAll()throws Exception;
}
