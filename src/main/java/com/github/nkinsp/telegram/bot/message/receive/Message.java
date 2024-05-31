package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.github.nkinsp.telegram.bot.bo.Chat;
import com.github.nkinsp.telegram.bot.bo.ChatUser;
import com.github.nkinsp.telegram.bot.enums.MessageType;
import lombok.Data;

import java.util.*;

@Data
public class Message {

    @JSONField(name = "message_id")
    private Long id;

    private ChatUser from;

    private Chat chat;

    private Long date;

    private String text;

    private String caption;

    private List<Photo> photo = new ArrayList<>();

    private Video video;

    private Voice voice;

    private Document document;

    @JSONField(name = "media_group_id")
    private String mediaGroupId;

    private JSONObject rawData;


    public MessageType type(){

        Map<String,MessageType> typeMap = new LinkedHashMap<>();

        typeMap.put("forward_origin",MessageType.FORWARD);
        typeMap.put("new_chat_member",MessageType.JOIN_GROUP);
        typeMap.put("left_chat_member",MessageType.LEFT_GROUP);
        typeMap.put("text",MessageType.TEXT);
        typeMap.put("photo",MessageType.PHOTO);
        typeMap.put("video",MessageType.VIDEO);
        typeMap.put("voice",MessageType.VOICE);
        typeMap.put("document",MessageType.DOCUMENT);



        for (Map.Entry<String, MessageType> entry : typeMap.entrySet()) {

            if(rawData.containsKey(entry.getKey())){
                return entry.getValue();
            }

        }
        return  null;

    }








}
