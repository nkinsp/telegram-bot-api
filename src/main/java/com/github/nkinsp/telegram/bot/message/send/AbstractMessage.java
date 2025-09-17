package com.github.nkinsp.telegram.bot.message.send;

import com.alibaba.fastjson2.JSONObject;
import com.github.nkinsp.telegram.bot.message.send.reply.ReplyKeyboardMarkup;
import com.github.nkinsp.telegram.bot.message.send.reply.ReplyMarkup;
import lombok.Data;

@Data
public abstract class AbstractMessage<T> implements IMessage {



    protected JSONObject message = new JSONObject();




    protected T self(){
        return (T) this;
    }


    public JSONObject getMessage() {
        return message;
    }

    public T put(String name, Object value){

        message.put(name,value);

        return  self();
    }


    public T to(String to) {

        return put("chat_id",to);

    }

    public T replay(String messageId) {

        return put("reply_parameters",new JSONObject().fluentPut("message_id",messageId));
    }

    public T replay(JSONObject replay){

        return put("reply_parameters",replay);
    }

    public T disableNotification(boolean disable){

        return put("disable_notification",disable);

    }

    public T protectContent(boolean protect){

        return put("protect_content",protect);
    }


    public T replyMarkup(ReplyMarkup replyMarkup){

        return put("reply_markup",replyMarkup);
    }




    @Override
    public String serialize() {
        return message.toJSONString();
    }

}
