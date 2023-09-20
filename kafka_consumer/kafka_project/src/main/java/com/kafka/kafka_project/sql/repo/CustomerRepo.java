package com.kafka.kafka_project.sql.repo;

import com.kafka.kafka_project.sql.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,Integer> {
    @Query(value = "Select * from customer_entity where customer_id = ?;", nativeQuery = true)
    CustomerEntity findById(int customerId);

//    @Query(value = "Select customer_id from customer_entity where customer_id = ?;", nativeQuery = true)
//    CustomerEntity findByCustomerId(int customerId);

}
