package com.endpoints.url.casandra.repo;

import com.endpoints.url.casandra.table.OrderDetailsTable;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;


import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends CassandraRepository<OrderDetailsTable, Integer> {

}


