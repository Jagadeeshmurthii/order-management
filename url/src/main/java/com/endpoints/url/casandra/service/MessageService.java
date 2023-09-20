package com.endpoints.url.casandra.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.endpoints.url.response.MessageResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public MessageResponse getMessageDetails(UUID msgId) {
        try {
            CqlSession session = CqlSession.builder().build();
            String query = "select msgstring  from demo.messagetable where msgid=? ALLOW FILTERING";
            ResultSet resultSet = session.execute(query, msgId);
            Row row = resultSet.one();
            logger.info("Get message details using msgId: " + msgId);
            if (row == null) {
                logger.error("Message not found with msgId: " + msgId);
            }
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setOrderDetails(row.getString("msgString"));
            return messageResponse;
        } catch (Exception e) {
            logger.error("Error while getting message details by msgId: " + msgId, e);
            return null;
        }
    }

    public MessageResponse getMessageUsingEntityId(int entityId) {
        try {
            CqlSession session = CqlSession.builder().build();
            String query = "select msgstring  from demo.messagetable where entityid=? ALLOW FILTERING";
            ResultSet resultSet = session.execute(query, entityId);
            Row row = resultSet.one();
            logger.info("Get message details using msgId: " + entityId);
            if (row == null) {
                logger.error("Message not found with msgId: " + entityId);
            }
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setOrderDetails(row.getString("msgString"));
            return messageResponse;
        } catch (Exception e) {
            logger.error("Error while getting message details by msgId: " + entityId, e);
            return null;
        }
    }

}

