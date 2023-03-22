package com.hvn.realtimechatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String sender;
    private String content;
    private String timestamp;
}
