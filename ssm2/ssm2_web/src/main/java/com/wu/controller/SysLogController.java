package com.wu.controller;

import com.wu.pojo.SysLog;
import com.wu.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 日志的视图层控制类
 */
@RequestMapping("/sysLog")
@Controller
public class SysLogController {

    /**********************************************************/
    @Autowired
    private SysLogService sysLogService;
    /*****************************************************************/

    @RequestMapping("/findAll")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        //调用业务层查询方法
        List<SysLog> sysLogs = sysLogService.findAll();
        //将查询到的日志信息保存到Model当中
        mv.addObject("sysLogs",sysLogs);
        //跳转到展示界面
        mv.setViewName("syslog-list");
        return mv;
    }
}
