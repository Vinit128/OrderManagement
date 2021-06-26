package com.egen.Service.kafka.producer;

import com.egen.Model.Order;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class producerServiceimpl {

    private final KafkaTemplate<String, Order> customerKafkaTemplate;

    @Value("${kafka.topic.order.name}")
    private String ORDER_TOPIC;



    public producerServiceimpl(KafkaTemplate<String, Order> kafkaTemplate, KafkaTemplate<String, Order> customerKafkaTemplate) {

        this.customerKafkaTemplate = customerKafkaTemplate;
    }

    public boolean sendOrder(Order order) {
        //log.info(String.format("Producing message: %s", orderDTO));

        customerKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, Order>> future = t.send(ORDER_TOPIC, order);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(SendResult<String, Order> result) {
                    RecordMetadata sentOrder = result.getRecordMetadata();
                    //log.info(String.format("=>{}", sentOrder.offset(), "{}", sentOrder.topic()));
                }
            });
            return true;
        });
        return true;
    }


}
