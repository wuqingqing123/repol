package com.wu.service.impl;

import com.wu.dao.SysLogDao;
import com.wu.pojo.SysLog;
import com.wu.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志的业务层实现类
 */
@Service("sysLogServce")
@Transactional
public class SysLogServiceImpl  implements SysLogService{

    /*********************************************************************/
    @Autowired
    private SysLogDao sysLogDao;
    /************************************************************************/


    /**
     * 保存日志信息
     * @param log
     * @throws Exception
     */
    public void save(SysLog log) throws Exception {
        //调用持久层保存方法
        sysLogDao.save(log);
    }

    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
