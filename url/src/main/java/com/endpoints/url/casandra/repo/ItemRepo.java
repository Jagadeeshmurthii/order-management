package com.endpoints.url.casandra.repo;


import com.endpoints.url.casandra.table.ItemTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends CassandraRepository<ItemTable, Integer> {

}
