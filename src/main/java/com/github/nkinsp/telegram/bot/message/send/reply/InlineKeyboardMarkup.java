package com.github.nkinsp.telegram.bot.message.send.reply;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


@Data
public class InlineKeyboardMarkup implements ReplyMarkup {

    @JSONField(name = "inline_keyboard")
    private List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();


    public InlineKeyboardMarkup addButtons(List<InlineKeyboardButton> buttons){

        this.inlineKeyboard.add(buttons);
        return this;
    }

    public InlineKeyboardMarkup addButton(Consumer<List<InlineKeyboardButton>> consumer){

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        consumer.accept(buttons);

        this.inlineKeyboard.add(buttons);

        return this;
    }


}
