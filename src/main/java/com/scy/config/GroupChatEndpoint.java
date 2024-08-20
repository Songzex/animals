package com.scy.config;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scy.mapper.UserAdditionalMapper;
import com.scy.mapper.UserMapper;
import com.scy.pojo.User;
import com.scy.pojo.UserAdditional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 群聊
 */
// 服务器端 WebSocket 处理类
@ServerEndpoint(value = "/websockets/{groupId}/{username}")
@Component
@Slf4j
public class GroupChatEndpoint {
    @Autowired
    UserAdditionalMapper userAdditionalMapper;
    @Autowired
    UserMapper userMapper;

    private static final Map<String, Set<Session>> groups = new ConcurrentHashMap<>();
    /**
     * 打开连接
     * @param session
     * @param groupId
     * @param username
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("groupId") String groupId, @PathParam("username") String username) {
        groups.computeIfAbsent(groupId, k -> ConcurrentHashMap.newKeySet()).add(session);
        log.info("群组WebSocket connection opened for user {} in group {},session{}", username, groupId,session);
    }

    /**
     * 发送收发消息
     * @param message
     * @param session
     * @param groupId
     * @param senderUsername
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("groupId") String groupId, @PathParam("username") String senderUsername) {
        Set<Session> groupMembers = groups.get(groupId);
//        Set<Session> groupMembers1 = groups.get(groupId);
//        groupMembers1.remove(session);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 将 JSON 数据解析为 Map 对象
            Map<String, String> emailData = objectMapper.readValue(message, HashMap.class);

            // 打印 Map 集合
         /*   System.out.println("Map 集合中的数据：");
            for (Map.Entry<String, String> entry : emailData.entrySet()) {
                System.out.println(entry.getKey() + "key:value " + entry.getValue());
            }*/
            String text = emailData.get("text");
            String photo= emailData.get("photo");
            String string = String.valueOf(groupId);
            System.out.println(groupId);
            StringBuilder append1 = new StringBuilder(string);
            StringBuilder append = new StringBuilder(text).append(append1);
            // 构造 JSON 对象
            JSONObject responseData = new JSONObject();
            responseData.put("text", append);
            responseData.put("name", senderUsername);
            responseData.put("photo", photo);
            String dataString = responseData.toString();
            if (groupMembers != null) {
                for (Session member : groupMembers) {
                    if(member==session){
                        log.warn("不能给自己发信息", groupId);
                    }else {
                    try {
                        // 将消息广播给群组内的所有成员
                        member.getBasicRemote().sendText(dataString);
                    } catch (IOException e) {
                        log.error("Error broadcasting message in group {}: {}", groupId, e.getMessage());
                    }
                }}
            } else {
                log.warn("Group {} does not exist", groupId);
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        }

    /**
     * 关闭连接触发的方法
     * @param session
     * @param groupId
     * @param username
     */
    @OnClose
    public void onClose(Session session, @PathParam("groupId") String groupId, @PathParam("username") String username) {
        Set<Session> groupMembers = groups.get(groupId);
        if (groupMembers != null) {
            groupMembers.remove(session);
            log.info("WebSocket connection closed for user {} in group {}", username, groupId);
        }
    }
}