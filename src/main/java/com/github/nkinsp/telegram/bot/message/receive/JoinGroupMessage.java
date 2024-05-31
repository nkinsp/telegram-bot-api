package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.annotation.JSONField;
import com.github.nkinsp.telegram.bot.bo.Chat;
import com.github.nkinsp.telegram.bot.bo.ChatUser;
import lombok.Data;

@Data
public class JoinGroupMessage {

    private Chat chat;

    private ChatUser from;

    private Long date;

    @JSONField(name = "new_chat_member")
    private NewChat newChat;

    @Data
    public static class NewChat {

        private String status;

        private ChatUser user;

    }
}
