package com.endpoints.url.casandra.repo;

import com.endpoints.url.casandra.table.MessageTable;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MessageRepo extends CassandraRepository<MessageTable, UUID>{

}
