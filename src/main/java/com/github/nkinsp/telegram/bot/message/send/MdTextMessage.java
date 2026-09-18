package com.github.nkinsp.telegram.bot.message.send;

import java.util.ArrayList;
import java.util.List;

public class MdTextMessage extends AbstractMessage<MdTextMessage>{


    protected final List<String> texts = new ArrayList<String>();

    @Override
    public String messageType() {
        return "sendMessage";
    }


    public MdTextMessage text(String text){

        texts.add(text);

        return this;
    }



    @Override
    public String serialize() {

        put("parse_mode","MarkdownV2");

        put("text", String.join("\n", texts));

        return super.serialize();
    }

}
