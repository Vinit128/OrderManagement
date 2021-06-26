package com.egen.Service.kafka.consumer;

import ch.qos.logback.classic.Logger;
import com.egen.Model.Order;
import com.egen.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;


@Service
public class ConsumerServiceImp {
    public OrderService orderService;

    @Autowired
    public ConsumerServiceImp(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "${kafka.topic.order.groupId}")
    public void consumeCustomerData(@Header(KafkaHeaders.OFFSET) Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)  String key,
                                                Order order) {
        orderService.placeOrder(order);
    }

}
