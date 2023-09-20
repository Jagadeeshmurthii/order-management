package com.endpoints.url.response;


import lombok.Data;

@Data
public class ItemResponse {

    private  String itemName;
    private int totalNumberOfOrders;
    private int  qty;
}
