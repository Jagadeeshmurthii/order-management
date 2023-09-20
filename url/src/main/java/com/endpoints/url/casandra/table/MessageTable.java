package com.endpoints.url.casandra.table;//package com.kafka.kafka_project.casandra.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTable {
    @PrimaryKey
    private String msgId;
    private int entityId;
    private Timestamp msgCreateTs;
    private String msgString;
}
