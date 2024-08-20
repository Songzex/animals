package com.scy.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Rabbitmq {

    public     String  EXCHANGE;
    public      String  ROUTINGKEY;
    public       String  QUEUE_NAME;


}
