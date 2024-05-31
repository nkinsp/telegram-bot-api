package com.github.nkinsp.telegram.bot.message;

import com.github.nkinsp.telegram.bot.bo.Bot;
import com.github.nkinsp.telegram.bot.message.receive.CallbackMessage;
import com.github.nkinsp.telegram.bot.message.receive.JoinGroupMessage;
import com.github.nkinsp.telegram.bot.message.receive.LeftGroupMessage;
import com.github.nkinsp.telegram.bot.message.receive.ReceivedMessage;

public interface MessageHandler {


    /**
     * 用户加入群组事件
     * @param bot
     * @param message
     */
    void onUserJoinGroup(Bot bot, JoinGroupMessage message);

    /**
     * 用户离开群
     * @param bot
     * @param message
     */
    void onUserLeftGroup(Bot bot,LeftGroupMessage message);


    /**
     * 机器人加入群事件
     * @param bot
     * @param message
     */
    void onRobotJoinGroup(Bot bot,JoinGroupMessage message);


    /**
     * 机器人离开群
     * @param bot
     * @param message
     */
    void onRobotLeftGroup(Bot bot, LeftGroupMessage message);


    /**
     * 消息事件
     * @param bot
     * @param message
     */
    void onMessage(Bot bot, ReceivedMessage message);


    /**
     * 消息回调事件
     * @param bot
     * @param message
     */
    void onCallbackQueryMessage(Bot bot, CallbackMessage message);

}
