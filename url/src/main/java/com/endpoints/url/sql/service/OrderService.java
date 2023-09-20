package com.endpoints.url.sql.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.endpoints.url.casandra.repo.OrderDetailsRepo;
import com.endpoints.url.sql.model.OrderEntity;
import com.endpoints.url.sql.repo.OrderRepo;
import com.endpoints.url.response.OrderResponse;
import com.endpoints.url.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



import static com.endpoints.url.utils.ErrorCodes.INVALID_CUSTOMER_ID;

@Service
public class OrderService {


    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;


    public List<OrderResponse> getAllOrdersByCustomerId(int customerId) {
        logger.info("Get All Orders By CustomerId");
        try {
            List<OrderEntity> orders = orderRepo.findById(customerId);
            CqlSession session = CqlSession.builder().build();
            List<OrderResponse> orderResponses = new ArrayList<>();
            for (OrderEntity order : orders) {
                String numberOfItemsQuery = "SELECT COUNT(itemId) FROM demo.orderdetailstable WHERE orderid= ? ALLOW FILTERING";
                ResultSet numberOfItemsResult = session.execute(numberOfItemsQuery, order.getOrderId());
                Row numberOfItemsRow = numberOfItemsResult.one();
                long totalNumberOfItems = numberOfItemsRow.getLong(0);
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrderId(order.getOrderId());
                orderResponse.setCost(order.getOrderCost());
                orderResponse.setNumberOfItems((int) totalNumberOfItems);
                orderResponse.setStatus(order.getOrderStatus());
                orderResponses.add(orderResponse);
            }
            return orderResponses;
        } catch (Exception e) {
            logger.error("Error while retrieving orders for customer ID: " + customerId, e);
            throw new ServiceException(INVALID_CUSTOMER_ID.getErrorCode(), INVALID_CUSTOMER_ID.getErrorDesc());
        }
    }

    public List<OrderResponse> getAllOrdersByPhone(String phone) {
        logger.info("Get All Orders By CustomerId");
        try {
            List<OrderEntity> orders = orderRepo.findByPhone(phone);
            CqlSession session = CqlSession.builder().build();
            List<OrderResponse> orderResponses = new ArrayList<>();
            for (OrderEntity order : orders) {
                String numberOfItemsQuery = "SELECT COUNT(itemId) FROM demo.orderdetailstable WHERE orderid= ? ALLOW FILTERING";
                ResultSet numberOfItemsResult = session.execute(numberOfItemsQuery, order.getOrderId());
                Row numberOfItemsRow = numberOfItemsResult.one();
                long totalNumberOfItems = numberOfItemsRow.getLong(0);
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrderId(order.getOrderId());
                orderResponse.setCost(order.getOrderCost());
                orderResponse.setNumberOfItems((int) totalNumberOfItems);
                orderResponse.setStatus(order.getOrderStatus());
                orderResponses.add(orderResponse);
            }
            return orderResponses;
        } catch (Exception e) {
            logger.error("Error while retrieving orders for customer ID: " + phone, e);
            throw new ServiceException(INVALID_CUSTOMER_ID.getErrorCode(), INVALID_CUSTOMER_ID.getErrorDesc());
        }
    }

    public List<OrderResponse> getAllOrdersByEmail(String email) {
        logger.info("Get All Orders By CustomerId");
        try {
            List<OrderEntity> orders = orderRepo.findByEmail(email);
            CqlSession session = CqlSession.builder().build();
            List<OrderResponse> orderResponses = new ArrayList<>();
            for (OrderEntity order : orders) {
                String numberOfItemsQuery = "SELECT COUNT(itemId) FROM demo.orderdetailstable WHERE orderid= ? ALLOW FILTERING";
                ResultSet numberOfItemsResult = session.execute(numberOfItemsQuery, order.getOrderId());
                Row numberOfItemsRow = numberOfItemsResult.one();
                long totalNumberOfItems = numberOfItemsRow.getLong(0);
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrderId(order.getOrderId());
                orderResponse.setCost(order.getOrderCost());
                orderResponse.setNumberOfItems((int) totalNumberOfItems);
                orderResponse.setStatus(order.getOrderStatus());
                orderResponses.add(orderResponse);
            }
            return orderResponses;
        } catch (Exception e) {
            logger.error("Error while retrieving orders for customer ID: " + email, e);
            throw new ServiceException(INVALID_CUSTOMER_ID.getErrorCode(), INVALID_CUSTOMER_ID.getErrorDesc());
        }
    }

}
