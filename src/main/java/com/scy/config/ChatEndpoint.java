package com.scy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 服务器端 WebSocket 处理类
@ServerEndpoint(value = "/websockets/{username}")
@Component
@Slf4j
public class ChatEndpoint {

    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
        log.info("WebSocket connection opened for user: {}", username);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String senderUsername) {
        // 解析消息，确定接收者和消息内容
        // 假设消息格式为 "receiverUsername:message"
        String[] parts = message.split(":", 2);
        String receiverUsername = parts[0];
        String messageContent = parts[1];

        // 根据接收者用户名获取接收者的 WebSocket 连接
        Session receiverSession = sessions.get(receiverUsername);
        if (receiverSession != null && receiverSession.isOpen()) {
            try {
                // 将消息发送给接收者
                receiverSession.getBasicRemote().sendText(messageContent);
            } catch (IOException e) {
                log.error("Error sending message to user: {}", receiverUsername, e);
            }
        } else {
            log.warn("User {} is not connected or session is closed", receiverUsername);
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
        log.info("WebSocket connection closed for user: {}", username);
    }
}
