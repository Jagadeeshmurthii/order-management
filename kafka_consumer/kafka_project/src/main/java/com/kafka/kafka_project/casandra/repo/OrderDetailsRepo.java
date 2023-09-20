package com.kafka.kafka_project.casandra.repo;

import com.kafka.kafka_project.casandra.table.OrderDetailsTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends CassandraRepository<OrderDetailsTable,Integer> {
}
