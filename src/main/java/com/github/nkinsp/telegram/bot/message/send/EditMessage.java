package com.github.nkinsp.telegram.bot.message.send;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class EditMessage<T> extends AbstractMessage<T>{


    public T messageId(String id){

        put("message_id",id);

        return self();
    }
}
