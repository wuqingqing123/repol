package com.wu.controller;


import com.wu.dao.SysLogDao;
import com.wu.pojo.SysLog;
import com.wu.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志处理类
 */
@Component
@Aspect
public class LogAop {

    /**********************************私有属性********************************************/
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    /**************************************************************************************/
    private Date startTime; //访问开始时间
    private Class executionClass; //访问的类
    private Method executionMethod; //访问的方法


    /*******************************************************************************/

    /**
     * 前置通知
     * 主要获取访问时间、访问的类、访问的方法
     */

    @Pointcut("execution(* com.wu.controller.*.*(..)) && !execution(* com.wu.controller.SysLogController.*(..))")
    private void ptc() {
    }


    @Before("ptc()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        startTime = new Date(); //访问时间
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs(); //获取访问的方法的参数
        if (args == null || args.length == 0) {
            //获取无参数方法
            executionMethod = executionClass.getMethod(methodName);

        } else {
            //有参数，就将args中所有元素遍历，获取对应的Class，装到一个Class[]
            //创建一个数组对象
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            //获取有参数方法
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     */
    @After("ptc()")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问的时长
        Long time = new Date().getTime() - startTime.getTime();

        String url = "";

        //获取url
        if (executionClass != null && executionMethod != null && executionClass != LogAop.class) {

            //获取类上的url
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //获取方法上的@RequestMapping()
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        //获取访问的Ip地址
        String ip = request.getRemoteAddr();

        //获取当前操作用户
        SecurityContext context = SecurityContextHolder.getContext();
        //从上下文获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //获取对象方法二
//        User user1 = (User)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

        //将日志相关信息封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);

        //调用Service完成操作
        sysLogService.save(sysLog);
    }
}
