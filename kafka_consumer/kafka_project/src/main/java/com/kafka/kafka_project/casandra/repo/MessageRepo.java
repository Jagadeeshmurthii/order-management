package com.kafka.kafka_project.casandra.repo;

import com.kafka.kafka_project.casandra.table.MessageTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MessageRepo extends CassandraRepository<MessageTable, UUID> {
}
