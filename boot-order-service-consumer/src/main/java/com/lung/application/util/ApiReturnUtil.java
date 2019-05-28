package com.lung.application.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ApiReturnUtil
 * @Author: chinpanglung
 * @Date: 19-5-27 下午4:49
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
public class ApiReturnUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    public static ApiReturnObject error(Object errorMessage) {
        System.out.println(errorMessage);
        List<Object> object = new ArrayList<Object>();
        return new ApiReturnObject("01", errorMessage, object);
    }

    public static ApiReturnObject error(Object errorMessage, Object returnObject) {
        List<Object> object = new ArrayList<Object>();
        object.add(returnObject);
        return new ApiReturnObject("01", errorMessage, object);
    }

    public static ApiReturnObject success(Object returnObject) {
        if (returnObject instanceof java.util.List) {
            return new ApiReturnObject("00", "success", returnObject);
        } else {
            List<Object> object = new ArrayList<Object>();
            object.add(returnObject);
            return new ApiReturnObject("00", "success", object);
        }
    }

    public static ApiReturnObject success(Object errorMessage, Object returnObject) {
        if (returnObject instanceof java.util.List) {
            return new ApiReturnObject("00", errorMessage, returnObject);
        } else {
            List<Object> object = new ArrayList<Object>();
            object.add(returnObject);
            return new ApiReturnObject("00", errorMessage, object);
        }
    }
}
