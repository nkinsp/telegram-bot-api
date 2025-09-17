package com.github.nkinsp.telegram.bot.message.send;

import com.alibaba.fastjson2.JSONObject;

import java.lang.reflect.InvocationTargetException;

public interface IMessage {

    String serialize();

    String messageType();

    JSONObject getMessage();



    static <T> T of(Class<T> message){
        try {
            return message.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    static TextMessage ofTextMessage(){
        return new TextMessage();
    }





}
