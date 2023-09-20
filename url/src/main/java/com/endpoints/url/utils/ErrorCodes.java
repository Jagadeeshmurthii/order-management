package com.endpoints.url.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum ErrorCodes {

    INVALID_CUSTOMER_ID("1003","Failed to retrieve orders for customer ID"),
    INVALID_PHONE_NUMBER("1004","The given phone num is incorrect"),
    INVALID_EMAIL_ID("1005","The given email_id is incorrect"),
    INVALID_ID("1001", "Invalid ID"),
    TABLE_ALREADY_EXIST("1005","Table already exist"),
    ID_NOT_FOUND("1002","Id not found");
    private final String errorCode;
    private final String errorDesc;

}
