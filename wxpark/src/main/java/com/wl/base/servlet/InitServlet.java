package com.wl.base.servlet;

import com.wl.common.utils.SystemConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class InitServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitServlet.class);

    @Override
    public void init() throws ServletException {
        try {
            SystemConfigUtil.init();
        } catch (IOException e) {
            LOGGER.error("系统初始化数据异常", e);
            throw new ServletException();
        }
        LOGGER.debug("系统初始化数据");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
