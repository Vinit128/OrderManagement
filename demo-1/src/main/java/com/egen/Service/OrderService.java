package com.egen.Service;




import com.egen.Model.Order;


import java.time.ZonedDateTime;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(String orderId);

    List<Order> getAllOrdersWithinInterval(ZonedDateTime startTime, ZonedDateTime endTime);

    List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

    Order placeOrder(Order order);

    Order cancelOrder(String id);

    Order updateOrder(String orderId, Order order);

}