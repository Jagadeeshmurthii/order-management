package com.endpoints.url.casandra.service;

import com.endpoints.url.utils.ServiceException;
import org.aspectj.apache.bcel.generic.TABLESWITCH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.datastax.oss.driver.api.core.CqlSession;

import static com.endpoints.url.utils.ErrorCodes.TABLE_ALREADY_EXIST;

@Service
public class CassandraTableCreationService {

    private final CqlSession cqlSession;

    @Autowired
    public CassandraTableCreationService(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
    }

    public void createItemTable() {
        String createTableCql = "CREATE TABLE IF NOT EXISTS demo.itemtable (" +
                "itemId int PRIMARY KEY," +
                "itemName TEXT," +
                "itemDesc TEXT," +
                "itemCost int," +
                "createTs TIMESTAMP," +
                "updateTs TIMESTAMP" +
                ");";

        cqlSession.execute(createTableCql);
    }

    public void createOrderDetailsTable() {
        String createTableCql = "CREATE TABLE IF NOT EXISTS demo.orderdetailstable (" +
                "orderDetailsId UUID," +
                "orderId int," +
                "itemId int," +
                "itemName TEXT," +
                "itemQty int," +
                "PRIMARY KEY ((orderId, itemId), orderDetailsId)" +
                ");";

        cqlSession.execute(createTableCql);
    }

    public void createMessageTable() {
        String createTableCql = "CREATE TABLE IF NOT EXISTS messagetable (" +
                "msgId UUID," +
                "entityId INT," +
                "msgCreateTs TIMESTAMP," +
                "msgString TEXT," +
                "PRIMARY KEY ((msgId, entityId), msgCreateTs)" +
                ");";

        try {
            cqlSession.execute(createTableCql);
            System.out.println("Table created successfully.");
        } catch (Exception e) {
//            throw new ServiceException(TABLE_ALREADY_EXIST.getErrorCode(),TABLE_ALREADY_EXIST.getErrorDesc());
            System.err.println("Table creation failed. The table may already exist.");
        }
    }


    public void dropMessageTable() {
        String dropTableCql = "DROP TABLE IF EXISTS messagetable;";

        cqlSession.execute(dropTableCql);
    }
}
