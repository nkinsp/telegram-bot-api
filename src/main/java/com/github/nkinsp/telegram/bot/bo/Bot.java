package com.github.nkinsp.telegram.bot.bo;

import com.alibaba.fastjson2.JSONObject;
import com.github.nkinsp.telegram.bot.api.BotApi;
import com.github.nkinsp.telegram.bot.message.send.EditMessage;
import com.github.nkinsp.telegram.bot.message.send.IMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;
import java.util.function.Supplier;

@Builder
@Data
public class Bot {

    private String id;

    private String apiKey;

    private String secretToken;

    private String userName;

    private Boolean dev = true;

    private BotSession session;

    /**
     * 发送消息
     * @param message
     * @return
     */
    public String sendMessage(IMessage message) {

        return api().sendMessage(message);

    }

    /**
     * 修改消息
     * @param message
     * @return
     * @param <T>
     */
    public <T> String editMessage(EditMessage<T> message) {

        return api().editMessage(message);

    }

    /**
     * 删除消息
     * @param chatId
     * @param messageId
     */
    public void deleteMessage(String chatId,String messageId){

        api().deleteMessage(chatId,messageId);

    }


    public BotApi api(){

        return new BotApi(this, dev);
    }



    public void openSession(Supplier<BotSession> func){

       this.session = func.get();

    }

    public BotSession getSession(){

        if(session == null){
            throw  new RuntimeException("session is not open");
        }

        return session;

    }





}
