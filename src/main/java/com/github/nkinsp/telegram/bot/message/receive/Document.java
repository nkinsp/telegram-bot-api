package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Document {

    @JSONField(name = "file_name")
    private String fileName;

    @JSONField(name = "mime_type")
    private String mimeType;

    private Thumb thumbnail;

    private Thumb thumb;


}
