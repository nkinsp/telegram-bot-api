package com.github.nkinsp.telegram.bot.message.send;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class ImageGroupMessage extends TextMessage{

    @Override
    public String messageType() {
        return "sendMediaGroup";
    }

    public ImageGroupMessage image(List<String> images){




        List<JSONObject> objects = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {

            String image = images.get(i);

            JSONObject data = new JSONObject();

            data.put("type","photo");
            data.put("media",image);

            if(i == 0){
                data.put("caption", String.join("\n", texts));
                data.put("parse_mode","HTML");
            }

            objects.add(data);

        }
        put("media", objects);


        return this;

    }

    @Override
    public String serialize() {


//        put("parse_mode", "HTML");

//        put("caption", String.join("\n", texts));

        return message.toJSONString();

    }
}
