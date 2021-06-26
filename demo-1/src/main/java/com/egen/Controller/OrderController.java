package com.egen.Controller;



import com.egen.Model.OrderDetails;
import com.egen.Response.Response;
import com.egen.Service.OrderService;
import com.egen.Model.Order;
import com.egen.Service.kafka.producer.producerServiceimpl;
import kafka.zookeeper.ResponseMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

    @RestController
    @ResponseBody
    @RequestMapping("order")
    public class OrderController {
        /**
         * implement the following endpoints
         */

        @Autowired
        private OrderService orderService;
        private producerServiceimpl producerService;

        @GetMapping("")
        public List<Order> getAllOrders(){
            Response<List<OrderDetails>> build = Response.<List<OrderDetails>>builder()
                    .meta(ResponseMetadata.builder()
                            .statusCode(200)
                            .statusMessage(Response.StatusMessage.SUCCESS.name()).build())
                    .data(OrderService.getAllOrders())
                    .build();
            return build;
        }
        @GetMapping("{id}")
        public Order getOrderById(@PathVariable("id") String orderId){
            OrderController service;
            Response<List<OrderDetails>> build = Response.<List<OrderDetails>>builder()
                    .meta(ResponseMetadata.builder()
                            .statusCode(200)
                            .statusMessage(Response.StatusMessage.SUCCESS.name()).build())
                    .data(OrderService.getOrderById())
                    .build();
            return build;

        @GetMapping("{start}/{end}")
        public List<Order> getAllOrdersWithinInterval (@PathVariable("start") ZonedDateTime startTime, @PathVariable("end") ZonedDateTime endTime){
            return orderService.getAllOrdersWithinInterval(startTime, endTime);
        }

        @GetMapping("getTopTen/{zip}")
        public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip){

            return orderService.top10OrdersWithHighestDollarAmountInZip(zip);
        }

        @PostMapping("")
        public Order placeOrder(@RequestBody Order order){
            return orderService.placeOrder(order);
        }

        @PutMapping("cancel/{id}")
        public Order cancelOrder(@PathVariable("id") String orderId){
            return orderService.cancelOrder(orderId);
        }


        @PutMapping("update/{id}")
        public Order updateOrder(@PathVariable("id") String orderId, @RequestBody Order order){
            return orderService.updateOrder(orderId, order);
        }
    }

