package com.github.nkinsp.telegram.bot.message.send.reply;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ReplyKeyboardRemoveMarkup implements ReplyMarkup {

    private Boolean remove_keyboard = true;

    private Boolean selective;
}
