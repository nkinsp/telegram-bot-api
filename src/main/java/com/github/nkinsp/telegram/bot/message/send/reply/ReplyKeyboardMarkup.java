package com.github.nkinsp.telegram.bot.message.send.reply;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Accessors(chain = true)
@Data
public class ReplyKeyboardMarkup implements ReplyMarkup {

    private List<List<KeyboardButton>> keyboard = new ArrayList<>();

    @JSONField(name = "is_persistent")
    private Boolean isPersistent;

    @JSONField(name = "resize_keyboard")
    private Boolean resizeKeyboard;

    @JSONField(name = "one_time_keyboard")
    private Boolean oneTimeKeyboard;

    @JSONField(name = "input_field_placeholder")
    private String inputFieldPlaceholder;

    private Boolean selective;

    public ReplyKeyboardMarkup addButtons(List<KeyboardButton> buttons){

        this.keyboard.add(buttons);
        return this;
    }

    public ReplyKeyboardMarkup addButton(Consumer<List<KeyboardButton>> consumer){

        List<KeyboardButton> buttons = new ArrayList<>();
        consumer.accept(buttons);

        this.keyboard.add(buttons);

        return this;
    }
}
