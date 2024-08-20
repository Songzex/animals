package com.scy.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@Component
public class RabbitmqPserveer {
  public  Rabbitmq rabbitmq=new Rabbitmq("SCY","","");
    @Bean
    public Queue queue(){
        return new Queue(rabbitmq.QUEUE_NAME);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(rabbitmq.EXCHANGE,true,true);
    }
    @Bean
    public Binding binding(DirectExchange exchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(rabbitmq.ROUTINGKEY);


    }

}
