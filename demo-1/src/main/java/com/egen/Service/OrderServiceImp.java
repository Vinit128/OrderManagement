package com.egen.Service;

import com.egen.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    private final OrderRepository repository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImplement( OrderRepository repository) {
        this.repository = repository;
    }

    public  List<OrderItem> getAllOrders() {
        return (List<OrderItem>) repository.findAll();
    }

    public Optional<OrderItem> getOrderById(String id) {
        Optional<OrderItem> order =  repository.findById(id);
        if (order == null){
            throw new OrderNotFoundException("Order with Order id" + id+ "is not available");
        }
        else{
            return Optional.of(order.get());
        }
    }

    public  List<OrderItem> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

//        return repository.getAllOrdersWithInInterval(startTime,endTime);
        return repository.getAllOrdersWithInInterval(startTime,endTime);
    }


    public  List<OrderItem> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<OrderItem>  orders = (List<OrderItem>) repository.findAll();
        List<OrderItem> zipOrders = new ArrayList<>();

        for(OrderItem order:orders){
            if(order.getShippingAddress().getZipcode().equalsIgnoreCase(zip)){
                zipOrders.add(order);
            }
        }
        Collections.sort(zipOrders, new Comparator<OrderItem>() {
            @Override
            public int compare(OrderItem o1, OrderItem o2) {
                return Double.compare(o1.getTotalAmount(),o2.getTotalAmount());
            }
        });
        List<OrderItem> topOrders = new ArrayList<>();
        for(int i =0; i < 10; i++){
            topOrders.add(zipOrders.get(i));
        }
        return topOrders;
    }

    public OrderItem placeOrder(OrderItem orderItem) {
        return repository.save(orderItem);
    }


    public  OrderItem cancelOrder(OrderItem order) {
        OrderItem oi  = repository.findById(order.getId()).get();
        oi.setOrderStatus(OrderStatus.CANCEL);
        return repository.save(oi);
    }


    public  OrderItem updateOrder(OrderItem order) {
        return repository.save(order);
    }

    public String createRandomOrders(int num){
        PodamFactory factory =  new PodamFactoryImpl();
        TypeManufacturer<Integer> manufacturer = new IntTypeManufacturerImpl(){

            @Override
            public Integer getInteger(AttributeMetadata attributeMetadata){
                if(attributeMetadata.getPojoClass().getName().equalsIgnoreCase("java.sql.Timestamp")){
                    return PodamUtils.getIntegerInRange(0,999);
                }
                else{
                    return super.getInteger(attributeMetadata);
                }
            }
        };
        factory.getStrategy().addOrReplaceTypeManufacturer(int.class,manufacturer);
        for(int i=0; i<num;i++){
            OrderItem orderItem = factory.manufacturePojoWithFullData(OrderItem.class);
            this.placeOrder(orderItem);
        }
        return "success";
    }
}