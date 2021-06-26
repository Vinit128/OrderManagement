package com.egen.Repository;

import com.egen.Model.OrderDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.security.Timestamp;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDetails, String> {


    @Query("select ord from OrderItem ord JOIN fetch ord.payments JOIN fetch ord.items " +
            "where ord.orderPlacedTime > :startTime AND ord.orderPlacedTime < :endTime")
    List<OrderDetails> getAllOrdersWithInInterval(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

}
