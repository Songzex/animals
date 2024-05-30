package com.scy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// 服务器端 WebSocket 处理类
@ServerEndpoint(value = "/chat/{groupId}/{username}")
@Component
@Slf4j
public class GroupChatEndpoint {

    private static final Map<String, Set<Session>> groups = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("groupId") String groupId, @PathParam("username") String username) {
        groups.computeIfAbsent(groupId, k -> ConcurrentHashMap.newKeySet()).add(session);
        log.info("WebSocket connection opened for user {} in group {}", username, groupId);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("groupId") String groupId, @PathParam("username") String senderUsername) {
        Set<Session> groupMembers = groups.get(groupId);
        if (groupMembers != null) {
            for (Session member : groupMembers) {
                try {
                    // 将消息广播给群组内的所有成员
                    member.getBasicRemote().sendText(senderUsername + ": " + message);
                } catch (IOException e) {
                    log.error("Error broadcasting message in group {}: {}", groupId, e.getMessage());
                }
            }
        } else {
            log.warn("Group {} does not exist", groupId);
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("groupId") String groupId, @PathParam("username") String username) {
        Set<Session> groupMembers = groups.get(groupId);
        if (groupMembers != null) {
            groupMembers.remove(session);
            log.info("WebSocket connection closed for user {} in group {}", username, groupId);
        }
    }
}