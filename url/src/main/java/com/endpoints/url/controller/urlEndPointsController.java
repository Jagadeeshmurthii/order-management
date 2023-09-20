package com.endpoints.url.controller;

import com.endpoints.url.casandra.service.CassandraTableCreationService;
import com.endpoints.url.casandra.service.ItemService;
import com.endpoints.url.casandra.service.MessageService;
import com.endpoints.url.response.ItemResponse;
import com.endpoints.url.response.MessageResponse;
import com.endpoints.url.response.OrderResponse;
import com.endpoints.url.sql.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/urlEndpoint")
public class urlEndPointsController {

    Logger logger = LoggerFactory.getLogger(urlEndPointsController.class);
    @Autowired
    OrderService orderService;
    @Autowired
    CassandraTableCreationService tableCreationService;

    @Autowired
    ItemService itemService;

    @Autowired
    MessageService messageService;

    @GetMapping("/allOrderByCustomer/{customer_id}")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable int customer_id) {
        logger.info("get all using customer id" + customer_id);
        return new ResponseEntity<>(orderService.getAllOrdersByCustomerId(customer_id), HttpStatus.OK);

    }

    @GetMapping("/allOrdersByPhone/{phone}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByPhone(@PathVariable String phone) {
        logger.info("get all using customer id" + phone);
        return new ResponseEntity<>(orderService.getAllOrdersByPhone(phone), HttpStatus.OK);

    }

    @GetMapping("/allOrdersByEmail/{email}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByEmail(@PathVariable String email) {
        logger.info("get all using customer id" + email);
        return new ResponseEntity<>(orderService.getAllOrdersByEmail(email), HttpStatus.OK);

    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemResponse> getItemDetails(@PathVariable int itemId) {
        logger.info("get item details using item id" + itemId);
        return new ResponseEntity<>(itemService.findById(itemId), HttpStatus.OK);
    }

    @GetMapping("/messageByEntityId/{entityId}")
    public ResponseEntity<MessageResponse> getMessageDetails(@PathVariable int entityId) {
        logger.info("get message details for particular entityId " + entityId);
        return new ResponseEntity<>(messageService.getMessageUsingEntityId(entityId), HttpStatus.OK);
    }

    @GetMapping("/messageByMsgId/{msgId}")
    public ResponseEntity<MessageResponse> getMessageDetails(@PathVariable UUID msgId) {
        logger.info("get message details for particular msgId " + msgId);
        return new ResponseEntity<>(messageService.getMessageDetails(msgId), HttpStatus.OK);
    }

    @GetMapping("/create-itemTable")
    public String createItemTable() {
        tableCreationService.createItemTable();
        return "Item table created successfully";
    }
    @GetMapping("/create-orderDetailsTable")
    public String createOrderDetailsTable() {
        tableCreationService.createOrderDetailsTable();
        return "OrderDetails table created successfully";
    }
    @GetMapping("/create-messageTable")
    public String createMessageTable() {
        tableCreationService.createMessageTable();
        return "Message table created successfully";
    }
}

