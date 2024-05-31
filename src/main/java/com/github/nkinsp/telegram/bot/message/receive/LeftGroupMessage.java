package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.annotation.JSONField;
import com.github.nkinsp.telegram.bot.bo.Chat;
import com.github.nkinsp.telegram.bot.bo.ChatUser;
import lombok.Data;

@Data
public class LeftGroupMessage {

    @JSONField(name = "message_id")
    private Long id;

    private ChatUser from;

    private Chat chat;

    @JSONField(name = "left_chat_member")
    private ChatUser leftUser;
}
