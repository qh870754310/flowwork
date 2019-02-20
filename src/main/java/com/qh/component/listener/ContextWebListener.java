package com.qh.component.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2018/5/2.
 */
@WebListener
public class ContextWebListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ContextWebListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("lang", "zh");

        String webRoot = servletContextEvent.getServletContext().getContextPath();
        logger.info("加载 servlet...");
        servletContextEvent.getServletContext().setAttribute("webRoot", webRoot); //web根目录
        logger.info("Web根目录:" + servletContextEvent.getServletContext().getAttribute("webRoot"));
        servletContextEvent.getServletContext().setAttribute("resRoot", webRoot + "/static"); //资态资源根目录
        logger.info("静态资源根目录:" + servletContextEvent.getServletContext().getAttribute("resRoot"));


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
