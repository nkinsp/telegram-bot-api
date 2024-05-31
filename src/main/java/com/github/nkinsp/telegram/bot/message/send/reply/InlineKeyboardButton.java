package com.github.nkinsp.telegram.bot.message.send.reply;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class InlineKeyboardButton {

    private String text;

    private String url;

    @JSONField(name = "callback_data")
    private String callbackData;

    @JSONField(name = "web_app")
    private WebAppInfo webApp;



}
