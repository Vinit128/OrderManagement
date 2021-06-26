package com.egen.Service;

import com.egen.Exception.OrderNotFound;
import com.egen.Model.Order;
import com.egen.Model.OrderDetails;
import com.egen.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService{

    private final OrderRepository repository;
    @Autowired
    private OrderRepository orderRepository;
    private Object OrderDetails;

    public OrderServiceImplement( OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAllOrders() {
        return (List<OrderDetails>) repository.findAll();
    }

    public Optional<OrderDetails> getOrderById(String id) {
        Optional<OrderDetails> order =  repository.findById(id);
        if (order != null){
            return Optional.of(order.get());
        }
        else{

            throw new OrderNotFound("Order with Order id" + id+ "is not available");
        }
    }

    public  List<OrderDetails> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

//        return repository.getAllOrdersWithInInterval(startTime,endTime);
        return repository.getAllOrdersWithInInterval(startTime,endTime);
    }


    public  List<OrderDetails> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<OrderDetails>  orders = (List<OrderDetails>) repository.findAll();
        List<OrderDetails> zipOrders = new ArrayList<>();

        for(OrderDetails order:orders){
            if(order.getShippingAddress().getZipcode().equalsIgnoreCase(zip)){
                zipOrders.add(order);
            }
        }
        Collections.sort(zipOrders, new Comparator<OrderDetails>() {
            @Override
            public int compare(OrderDetails o1, OrderDetails o2) {
                return Double.compare(o1.getTotalAmount(),o2.getTotalAmount());
            }
        });
        List<OrderDetails> topOrders = new ArrayList<>();
        for(int i =0; i < 10; i++){
            topOrders.add(zipOrders.get(i));
        }
        return topOrders;
    }

    public OrderDetails placeOrder(OrderDetails orderItem) {
        return repository.save(orderItem);
    }


    public  OrderDetails cancelOrder(OrderDetails order) {
        OrderDetails oi  = repository.findById(order.getId()).get();
        oi.setOrderStatus(Order.CANCEL);
        return repository.save(oi);
    }


    public  OrderDetails updateOrder(OrderDetails order) {
        return repository.save(order);
    }


}