package com.hfzycj.annotation;

import com.hfzycj.domain.Log;
import com.hfzycj.domain.User;
import com.hfzycj.service.LogService;
import com.hfzycj.util.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Date;

import static com.hfzycj.util.StringUtil.getIpAddress;

/**
 * 系统日志切点
 */
@Aspect
@Component
public class SystemLogAspect {

    /**
     * Log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired
    private LogService logService;

    // Controller层 AOP
    @Pointcut("@annotation(com.hfzycj.annotation.SystemLog)")
    public void controllerAspect() {

    }

    @Before("controllerAspect()")
    public void before() {
        LOGGER.debug("@Before");
    }

    @AfterReturning("controllerAspect()")
    public void afterReturning() {
        LOGGER.debug("@AfterReturning");
    }

    @After("controllerAspect()")
    public void after() {
        LOGGER.debug("@After");
    }

    @AfterThrowing("controllerAspect()")
    public void afterThrowing() {
        LOGGER.debug("@AfterThrowing");
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        LOGGER.debug("@Around 开始时间：" + startTime);

        return writeLog(joinPoint, startTime);
    }

    /**
     * 写日志
     * @param joinPoint
     * @param startTime
     * @return
     * @throws Throwable
     */
    private Object writeLog(ProceedingJoinPoint joinPoint, long startTime) throws Throwable {
        Object object = joinPoint.proceed();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestFullPath = request.getRequestURL().toString();
        if (null != request.getQueryString()) {
            requestFullPath += "?" + URLDecoder.decode(request.getQueryString(), "UTF-8");
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        try {
            long endTime = System.currentTimeMillis();
            LOGGER.debug("@Around 结束时间：" + endTime);

            Log log = new Log();
            String methodAndDescription = getControllerMethodDescription(joinPoint);
            log.setLog_model(methodAndDescription.substring(0, methodAndDescription.indexOf(",")));
            log.setLog_description(methodAndDescription.substring(methodAndDescription.indexOf(",") + 1, methodAndDescription.length()));
            log.setLog_user_id(null == user ? 0L : user.getUser_id());
            log.setLog_user_name(null == user ? "" : user.getUser_name());
            log.setLog_ip(getIpAddress(request));
            log.setLog_url(requestFullPath);
            log.setLog_method((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setLog_response_time(endTime - startTime);
            log.setLog_detail(object.toString());
            log.setLog_create_time(new Date());
            logService.save(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String returnString = "";
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    returnString += method.getAnnotation(SystemLog.class).model();
                    returnString += ",";
                    returnString += method.getAnnotation(SystemLog.class).description();
                    break;
                }
            }
        }
        return returnString;
    }

}
