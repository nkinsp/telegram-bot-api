package com.github.nkinsp.telegram.bot.message.receive;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Thumb {

    @JSONField(name = "file_id")
    private String fileId;

    @JSONField(name = "file_unique_id")
    private String fileUniqueId;

    @JSONField(name = "file_size")
    private Long fileSize;

    private Integer width;

    private Integer height;
}
