package com.scy;

import com.scy.config.ShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication(scanBasePackages ="com.scy*")
@EnableWebSocket
@CrossOrigin(allowedHeaders = "content-type")
public class AnimalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

}
