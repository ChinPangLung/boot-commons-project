package com.lung.application.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lung.application.socket.DemoSocketServer;
import com.lung.application.util.ApiReturnObject;
import com.lung.application.util.ApiReturnUtil;
import com.lung.application.util.BasePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Title: SocketDemoController
 * @Author: chinpanglung
 * @Date: 19-5-27 下午4:47
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Controller
public class SocketDemoController {
    @ResponseBody
    @GetMapping("/index")
    public ApiReturnObject index(String data) {
        if (StringUtils.isEmpty(data)) {
            data = "hello spring-cloud-study";
        }
        return ApiReturnUtil.success(data);
    }

    @GetMapping("/socket/{cid}")
    public ModelAndView sockethtml(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("cid", cid);
        mav.addObject("basePath", "http://127.0.0.1:8081");
        return mav;
    }

    @GetMapping(value = "im")
    public ModelAndView imhtml() {
        ModelAndView modelAndView = new ModelAndView("im");
        return modelAndView;
    }

    @GetMapping("/basepath")
    @ResponseBody
    public ApiReturnObject basePath(HttpServletRequest request) {
        return ApiReturnUtil.success(BasePath.getBasePath(request));
    }

    @ResponseBody
    @RequestMapping("/socket/push/{sid}")
    public ApiReturnObject pushToWeb(@PathVariable String sid, String message) {
        try {
            DemoSocketServer.sendInfo(message, sid);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiReturnUtil.error(sid + "#" + e.getMessage());
        }
        return ApiReturnUtil.success(sid);
    }

}
