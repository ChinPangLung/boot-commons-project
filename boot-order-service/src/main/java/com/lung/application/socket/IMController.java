package com.lung.application.socket;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lung.application.util.ApiReturnUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: IMController
 * @Author: chinpanglung
 * @Date: 19-5-28 下午2:32
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@ServerEndpoint(value = "/im/{userId}")
@Component
@Log4j2
public class IMController {

    /**
     * 静态变量 用来记录当前的连接数量 应该设计成线程安全的
     */
    private static int onLineCount = 0;

    /**
     * 与某个客户端的连接会话 需要通过它给客户端发送数据
     */
    private Session session;

    /**
     * 使用map对象 便于根据UserId来获取对应的websocket
     */
    private static ConcurrentHashMap<String, IMController> webSocketList = new ConcurrentHashMap<>();

    /**
     * 接收的userId
     */
    private String userId = "";

    /**
     * 建立连接成功调用的方法
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;

        if (!webSocketList.containsKey(userId)) {
            webSocketList.put(userId, this);
            addOnlineCount();
        } else {
            log.info("该客户端已连接");
        }

        log.info("websocketList->" + JSON.toJSONString(webSocketList));
        log.info("有新的窗口开始监听：" + userId + ",当前人数为：" + getOnlineCount());
        this.userId = userId;
        sendMessage(JSON.toJSONString(ApiReturnUtil.success("连接成功!")));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketList.get(this.userId) != null) {
            webSocketList.remove(this.userId);
            subOnlineCount();
            log.info("有一个连接关闭! 当前的在线人数为:" + getOnlineCount());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口:" + this.userId + "的消息：" + message);
        if (StringUtils.isNotEmpty(message)) {
            JSONArray list = JSONArray.parseArray(message);
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = list.getJSONObject(i);
                String toUserId = object.getString("toUserId");
                String contentText = object.getString("contentText");
                object.put("fromUserId", this.userId);
                /**
                 * 传送数据到对应的用户websocket
                 */
                if (StringUtils.isNotEmpty(toUserId) && StringUtils.isNotEmpty(contentText)) {
                    IMController imController = webSocketList.get(toUserId);
                    if (imController != null) {
                        imController.sendMessage(JSON.toJSONString(ApiReturnUtil.success(object)));
                        //TODO 此处可以放置其他的业务逻辑 例如存储数据到数据库

                    }
                }
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("error");
        error.printStackTrace();
    }

    /**
     * 实现服务端发送消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam(value = "userId") String userId) {
        log.info("推送消息到窗口:" + userId + "，推送的内容是：" + message);
        Iterator<Map.Entry<String, IMController>> iterator = webSocketList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, IMController> next = iterator.next();
            try {
                if (next.getKey().equals(userId)) {
                    next.getValue().sendMessage(message);
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onLineCount;
    }

    public static synchronized void addOnlineCount() {
        IMController.onLineCount++;
    }

    public static synchronized void subOnlineCount() {
        IMController.onLineCount--;
    }

}
