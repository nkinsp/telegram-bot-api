package com.github.nkinsp.telegram.bot.message.send;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextEditMessage extends EditMessage<TextEditMessage>{

    private final List<String> texts = new ArrayList<String>();

    @Override
    public String messageType() {
        return "editMessageText";
    }

    public TextEditMessage text(String text){

        texts.add(text);

        return this;
    }

    @Override
    public String serialize() {

        put("parse_mode", "HTML");

        put("text", String.join("\n", texts));

        return super.serialize();
    }
}
