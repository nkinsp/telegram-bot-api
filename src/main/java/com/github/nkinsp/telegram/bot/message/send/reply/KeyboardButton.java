package com.github.nkinsp.telegram.bot.message.send.reply;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class KeyboardButton {

    private String text;

    @JSONField(name = "request_contact")
    private Boolean requestContact;

    @JSONField(name = "request_location")
    private Boolean requestLocation;

    @JSONField(name = "web_app")
    private WebAppInfo webApp;

}
