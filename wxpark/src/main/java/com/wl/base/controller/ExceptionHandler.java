package com.wl.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录异常日志
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    /**
     * Log
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) {
        // 记录异常日志
        LOGGER.error(String.valueOf(ex));
        return null;
    }

}
