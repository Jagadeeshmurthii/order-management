package com.endpoints.url.sql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;
    private int orderCost;
    private Timestamp orderCreateTs;
    private Timestamp orderUpdateTs;
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

}