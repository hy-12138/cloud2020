package com.hy.springCloud.service.impl;

import com.hy.springCloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class)// 可以理解为是一个消息的发送管道的定义
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;// 消息的发送管道

    @Override
    public String send() {

        String message = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(message).build());
        System.out.println("message:"+message);
        return message;
    }
}
