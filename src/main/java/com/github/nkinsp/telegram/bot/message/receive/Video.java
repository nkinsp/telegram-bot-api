package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Video {

    private Long duration;

    @JSONField(name = "file_name")
    private String fileName;

    @JSONField(name = "mime_type")
    private String mimeType;

    @JSONField(name = "file_id")
    private String fileId;

    @JSONField(name = "file_unique_id")
    private String fileUniqueId;

    @JSONField(name = "file_size")
    private Long fileSize;

    private Integer width;

    private Integer height;

    private Thumb thumbnail;

    private Thumb thumb;
}
