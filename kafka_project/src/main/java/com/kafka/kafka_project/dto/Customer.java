package com.kafka.kafka_project.dto;

import lombok.Data;

@Data
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String emailId;
}
