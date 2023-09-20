package com.kafka.kafka_project.casandra.repo;



import com.kafka.kafka_project.casandra.table.ItemTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends CassandraRepository<ItemTable,Integer> {


}
