package com.github.nkinsp.telegram.bot.message.send;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextMessage extends AbstractMessage<TextMessage>{


    protected final List<String> texts = new ArrayList<String>();

    @Override
    public String messageType() {
        return "sendMessage";
    }


    public TextMessage text(String text){

        texts.add(text);

        return this;
    }

    public TextMessage bold(String text){

        return  text("<b>"+text+"</b>");
    }

    public TextMessage link(String text,String url){

        return text(String.format("<a href=\"%s\">%s</a>",url,text));
    }


    @Override
    public String serialize() {

        put("parse_mode","HTML");

        put("text", String.join("\n", texts));

        return super.serialize();
    }

}
