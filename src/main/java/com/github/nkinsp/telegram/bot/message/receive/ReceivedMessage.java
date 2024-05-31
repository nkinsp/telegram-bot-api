package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReceivedMessage extends Message {

    @JSONField(name = "reply_to_message")
    private Message replyMessage;

}
