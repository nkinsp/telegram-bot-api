package com.github.nkinsp.telegram.bot.bo;

import com.github.nkinsp.telegram.bot.api.BotApi;
import com.github.nkinsp.telegram.bot.message.send.EditMessage;
import com.github.nkinsp.telegram.bot.message.send.IMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Bot {

    private String id;

    private String apiKey;

    private String secretToken;

    private String userName;

    private Boolean dev = true;

    /**
     * 发送消息
     * @param message
     * @return
     */
    public String sendMessage(IMessage message) {

        return api().sendMessage(message);

    }

    public <T> String editMessage(EditMessage<T> message) {

        return api().editMessage(message);

    }

    public BotApi api(){

        return new BotApi(this, dev);
    }


}
