package com.github.nkinsp.telegram.bot.message.send.reply;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ForceReplyMarkup implements ReplyMarkup {

    private Boolean force_reply = true;

    @JSONField(name = "input_field_placeholder")
    private String inputFieldPlaceholder;

    private Boolean selective;
}
