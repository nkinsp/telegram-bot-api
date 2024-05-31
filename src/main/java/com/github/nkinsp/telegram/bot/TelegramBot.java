package com.github.nkinsp.telegram.bot;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.nkinsp.telegram.bot.bo.Bot;
import com.github.nkinsp.telegram.bot.bo.ChatUser;
import com.github.nkinsp.telegram.bot.message.MessageHandler;
import com.github.nkinsp.telegram.bot.message.receive.CallbackMessage;
import com.github.nkinsp.telegram.bot.message.receive.JoinGroupMessage;
import com.github.nkinsp.telegram.bot.message.receive.LeftGroupMessage;
import com.github.nkinsp.telegram.bot.message.receive.ReceivedMessage;
import lombok.Data;

@Data
public class TelegramBot {


    private MessageHandler messageHandler;

    /**
     * 消息
     * @param bot
     * @param message
     */
    public void onMessage(Bot bot, String message) {

        if(messageHandler == null){

            throw  new RuntimeException("messageHandler is NULL");
        }

        JSONObject json = JSON.parseObject(message);

        //回调消息
        if (json.containsKey("callback_query")) {
            var callbackQuery = json.getObject("callback_query", CallbackMessage.class);
            messageHandler.onCallbackQueryMessage(bot,callbackQuery);
            return;
        }

        //机器人加入事件
        if (json.containsKey("my_chat_member")) {

            var joinGroupMessage = json.getObject("my_chat_member", JoinGroupMessage.class);
            doJoinGroupEvent(bot, joinGroupMessage);
            return;
        }

        if(json.containsKey("left_chat_member")){


            var leftMessage = json.getObject("left_chat_member", LeftGroupMessage.class);

            messageHandler.onUserLeftGroup(bot,leftMessage);

            return;
        }


        if (json.containsKey("message")) {

            var receiveMessage = json.getObject("message", ReceivedMessage.class);

            receiveMessage.setRawData(json.getJSONObject("message"));

            messageHandler.onMessage(bot,receiveMessage);
            return;

        }


    }

    /**
     * 加入群
     *
     * @param bot
     * @param message
     */
    private void doJoinGroupEvent(Bot bot, JoinGroupMessage message) {

        var chat = message.getNewChat();

        ChatUser user = chat.getUser();

        boolean isBot = user.getUserName().equals(bot.getUserName());


        if (chat.getStatus().equals("left")) {

            LeftGroupMessage leftMessage = new LeftGroupMessage();

            leftMessage.setLeftUser(chat.getUser());
            leftMessage.setFrom(message.getFrom());
            leftMessage.setChat(message.getChat());

            if(isBot){
                messageHandler.onRobotLeftGroup(bot,leftMessage);
            }else {
                messageHandler.onUserLeftGroup(bot,leftMessage);
            }
            return;
        }

        if(isBot){
            messageHandler.onRobotJoinGroup(bot,message);
        }else {
            messageHandler.onUserJoinGroup(bot,message);
        }
    }

    public TelegramBot(MessageHandler handler){
        this.messageHandler = handler;
    }

}