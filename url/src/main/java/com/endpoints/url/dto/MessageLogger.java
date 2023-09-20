package com.endpoints.url.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageLogger {

    private int msgId;
    private int orderId;
    private Timestamp msgCreateTs;
    private String msgString;
    private int customerId;
}
