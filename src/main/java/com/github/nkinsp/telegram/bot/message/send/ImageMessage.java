package com.github.nkinsp.telegram.bot.message.send;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ImageMessage extends TextMessage{

    @Override
    public String messageType() {
        return "sendPhoto";
    }

    public ImageMessage image(String image){

        put("photo",image);

        return this;

    }

    @Override
    public String serialize() {


        put("parse_mode", "HTML");

        put("caption", String.join("\n", texts));

        return message.toJSONString();

    }
}
