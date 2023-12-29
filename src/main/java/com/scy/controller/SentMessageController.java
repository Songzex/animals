package com.scy.controller;

import com.scy.config.WebSocketServer;
import com.scy.from.FromUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**发邮件**/
@RestController
@RequestMapping("email/")
@CrossOrigin
public class SentMessageController {
    @Autowired
    private  WebSocketServer webSocketServer;
          /* emailcode/ 发送注册验证码*/
         /* emailqinkuang发送进度信息*/
@RequestMapping("/test")
    public Object setComment(){
        webSocketServer.onMessage("test");
        return "null";

    }

}
