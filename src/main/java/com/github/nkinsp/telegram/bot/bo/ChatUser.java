package com.github.nkinsp.telegram.bot.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class ChatUser {

    private Long id;

    @JSONField(name = "first_name")
    private String firstName;

    @JSONField(name = "last_name")
    private String lastName;

    @JSONField(name = "username")
    private String userName;

    @JSONField(name = "is_bot")
    private Boolean isBot;

    @JSONField(name = "language_code")
    private String languageCode;


}
