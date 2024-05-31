package com.github.nkinsp.telegram.bot.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.github.nkinsp.telegram.bot.enums.ChatType;
import lombok.Data;

@Data
public class Chat {

    private String id;

    private String type;

    private String title;

    @JSONField(name = "first_name")
    private String firstName;

    @JSONField(name = "last_name")
    private String lastName;

    private String username;


}
