package com.github.nkinsp.telegram.bot.message.send;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CaptionEditMessage extends EditMessage<CaptionEditMessage>{

    private final List<String> texts = new ArrayList<String>();

    @Override
    public String messageType() {
        return "editMessageCaption";
    }

    public CaptionEditMessage caption(String caption){

        texts.add(caption);

        return this;
    }

    @Override
    public String serialize() {

        put("parse_mode", "HTML");

        put("caption", String.join("\n", texts));

        return super.serialize();
    }
}
