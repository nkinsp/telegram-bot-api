package com.github.nkinsp.telegram.bot.message.receive;

import com.github.nkinsp.telegram.bot.bo.ChatUser;
import lombok.Data;


@Data
public class CallbackMessage {


    private String id;

    private ChatUser from;

    private Message message;

    private String data;

}
