package com.scy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author 24022
 */
@SpringBootApplication(scanBasePackages ="com.scy*")
@EnableWebSocket
@CrossOrigin(allowedHeaders = "content-type")
public class AnimalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

}
