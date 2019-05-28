package com.lung.application.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: BasePath
 * @Author: chinpanglung
 * @Date: 19-5-27 下午4:50
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
public class BasePath {
    protected static String contextPath = null;
    protected static String basePath = null;
    protected static String realPath = null;

    public static String getBasePath(HttpServletRequest request) {
        contextPath = request.getContextPath();
        basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
        return basePath;
    }

    public static String getRealPath(HttpServletRequest request, String path) {
        ServletContext context = request.getSession().getServletContext();
        realPath = context.getRealPath(path);
        realPath = context.getRealPath(path) + "\\";
        return realPath;
    }

    public static String getMyRealPath(HttpServletRequest request, String path) {
        ServletContext context = request.getSession().getServletContext();
        realPath = context.getRealPath(path);
        realPath = context.getRealPath(path);
        return realPath;
    }
}
