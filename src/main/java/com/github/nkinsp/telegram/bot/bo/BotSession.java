package com.github.nkinsp.telegram.bot.bo;

public interface BotSession {

    BotSession setAttr(String name,Object value);

    <T> T getAttr(String name);

    void removeAttr(String name);

}
