package com.github.nkinsp.telegram.bot.message.send;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyMarkupEditMessage extends EditMessage<ReplyMarkupEditMessage>{
    @Override
    public String messageType() {
        return "editMessageReplyMarkup";
    }
}
