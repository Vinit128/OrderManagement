package com.egen.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.security.Timestamp;

public interface OrderRepository extends CrudRepository<OrderItem, String> {


    @Query("select ord from OrderItem ord JOIN fetch ord.payments JOIN fetch ord.items " +
            "where ord.orderPlacedTime > :startTime AND ord.orderPlacedTime < :endTime")
    List<OrderItem> getAllOrdersWithInInterval(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

}
