package com.github.nkinsp.telegram.bot.message.send;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class VideoMessage extends TextMessage{

    @Override
    public String messageType() {
        return "sendVideo";
    }

    public VideoMessage video(String image){

        put("video",image);

        return this;

    }

    @Override
    public String serialize() {


        put("parse_mode", "HTML");

        put("caption", String.join("\n", texts));

        return message.toJSONString();

    }
}
