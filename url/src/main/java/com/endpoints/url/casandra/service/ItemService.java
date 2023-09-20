package com.endpoints.url.casandra.service;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.endpoints.url.response.ItemResponse;
import com.endpoints.url.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.endpoints.url.utils.ErrorCodes.INVALID_ID;

@Service
public class ItemService {
    Logger logger = LoggerFactory.getLogger(ItemService.class);

//    @Autowired
//    @Qualifier("cqlSession")
//    private CqlSession cqlSession;

    public ItemResponse findById(int itemId) {
        try {
            String query = "SELECT * FROM demo.orderdetailstable WHERE itemId = ? ALLOW FILTERING";
            CqlSession session = CqlSession.builder().build();
            ResultSet resultSet =  session.execute(query, itemId);
            Row row = resultSet.one();
            if (row == null) {
                logger.error("Item not found with ID: " + itemId);
                throw new ServiceException(INVALID_ID.getErrorCode(), INVALID_ID.getErrorDesc());
            }
            logger.info("Get item details");
            ItemResponse itemResponse = new ItemResponse();
            itemResponse.setItemName(row.getString("itemName"));
            query = "SELECT count(*) FROM demo.orderdetailstable where itemid=? ALLOW FILTERING";
            resultSet = session.execute(query, itemId);
            Row countRow = resultSet.one();

            if (countRow != null) {
                Long orderCount = countRow.getLong(0);
                itemResponse.setTotalNumberOfOrders(Math.toIntExact(orderCount));
            }
            query = "SELECT count(*) FROM demo.orderdetailstable where itemid=? ALLOW FILTERING";
            resultSet = session.execute(query, itemId);
            Row quantity = resultSet.one();

            if (countRow != null) {
                Long itemQty = quantity.getLong(0);
                itemResponse.setQty(Math.toIntExact(itemQty));
            }
            return itemResponse;
        } catch (Exception e) {
            logger.error("Error while finding item by ID: " + itemId, e);
            return null;
        }
    }

}


