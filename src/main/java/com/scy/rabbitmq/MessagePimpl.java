package com.scy.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessagePimpl implements IMessageP {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String msg, RabbitmqPserveer pserveer) {
        rabbitTemplate.convertAndSend(pserveer.getRabbitmq().EXCHANGE,pserveer.getRabbitmq().ROUTINGKEY,msg);
    }
}
