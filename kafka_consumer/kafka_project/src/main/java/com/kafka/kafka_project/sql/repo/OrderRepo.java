package com.kafka.kafka_project.sql.repo;


import com.kafka.kafka_project.sql.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
    @Query(value = "Select * from order_entity where customer_id = ?;", nativeQuery = true)
    OrderEntity findById(int customerId);
}
