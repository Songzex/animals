package com.scy.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    private static CopyOnWriteArrayList<WebSocketServer> webSocketServers = new CopyOnWriteArrayList<>();
    private Session session;
// 打开链接
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketServers.add(this);
        System.out.println("WebSocket连接建立");
    }
//   关闭连接
    @OnClose
    public void onClose() {
        webSocketServers.remove(this);
        System.out.println("WebSocket连接关闭");
    }
// 错误触发
    @OnError
    public void onError(Throwable error) {
        System.err.println("WebSocket发生错误：" + error.getMessage());
    }
// 收到的信息
    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到消息：" + message);

        // 群发消息
        for (WebSocketServer server : webSocketServers) {
            try {
                server.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//发送信息
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


}

