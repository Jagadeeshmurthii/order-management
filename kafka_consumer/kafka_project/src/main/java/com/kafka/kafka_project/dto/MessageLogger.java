package com.kafka.kafka_project.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageLogger {
    private int msgId;
    private int entityId;
    private Timestamp msgCreateTs;
    private String msgString;
}
