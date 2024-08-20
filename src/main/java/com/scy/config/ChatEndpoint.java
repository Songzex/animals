package com.scy.config;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scy.mapper.UserAdditionalMapper;
import com.scy.mapper.UserMapper;
import com.scy.pojo.User;
import com.scy.pojo.UserAdditional;
import com.scy.service.UserAdditionalService;
import com.scy.service.UserService;
import com.scy.service.impl.UserAdditionalServiceImpl;
import com.scy.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单聊节点
 */
// 服务器端 WebSocket 处理类
@ServerEndpoint(value = "/websockets/{username}")
@Component
@Slf4j
public class ChatEndpoint {


    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
        log.info("webSocket连接");
        log.info("单聊WebSocket 连接 opened for 用户: {}", username);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String senderUsername) {
        log.info("收到的信息显示"+message);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 将 JSON 数据解析为 Map 对象
            Map<String, String> emailData = objectMapper.readValue(message, HashMap.class);

            // 打印 Map 集合
       /*     System.out.println("Map 集合中的数据：");
            for (Map.Entry<String, String> entry : emailData.entrySet()) {
                System.out.println(entry.getKey() + "key:value " + entry.getValue());
            }*/
            String receiverUsername = emailData.get("to");
            String text = emailData.get("text");
//            String string = new StringBuilder(senderUsername).toString();
         //   StringBuilder append = new StringBuilder(text);
            String photo= emailData.get("photo");
            // 构造 JSON 对象
            JSONObject responseData = new JSONObject();
            responseData.put("text", text);
            responseData.put("photo", photo);
            String dataString = responseData.toString();
            // 根据接收者用户名获取接收者的 WebSocket 连接
            Session receiverSession = sessions.get(receiverUsername);
            if (receiverSession != null && receiverSession.isOpen()) {
                try {
                    // 将消息发送给接收者
                    receiverSession.getBasicRemote().sendText(dataString);
                    System.out.println("gggg"+dataString);
                } catch (IOException e) {
                    log.error("Error sending message to user: {}", receiverUsername, e);
                }
            } else {
                log.warn("User {} is not connected or session is closed", receiverUsername);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
        log.info("WebSocket connection closed for user: {}", username);
    }
}
