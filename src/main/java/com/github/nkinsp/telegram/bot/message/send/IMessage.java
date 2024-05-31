package com.github.nkinsp.telegram.bot.message.send;

import java.lang.reflect.InvocationTargetException;

public interface IMessage {

    String serialize();

    String messageType();



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
