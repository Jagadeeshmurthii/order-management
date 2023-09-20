package com.endpoints.url.sql.repo;


import com.endpoints.url.sql.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "Select * from order_entity where customer_id =?;", nativeQuery = true)
    List<OrderEntity> findById(int customerId);

    @Query(value = "SELECT cust.customer_id AS cust_customer_id, ord.*\n" +
            "FROM customer_entity cust\n" +
            "LEFT JOIN order_entity ord ON cust.customer_id = ord.customer_id\n" +
            "WHERE cust.phone =?;", nativeQuery = true)
    List<OrderEntity> findByPhone(String phone);

    @Query(value = "SELECT cust.customer_id AS cust_customer_id, ord.*\n" +
            "FROM customer_entity cust\n" +
            "LEFT JOIN order_entity ord ON cust.customer_id = ord.customer_id\n" +
            "WHERE cust.email_id =?;", nativeQuery = true)
    List<OrderEntity> findByEmail(String email);
}
