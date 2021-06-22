package com.egen.Model;


// having multiple orders
public class OrderDetails {

    //b. provides an ability to have more than 1 item per order. What order schema in 2020
    //	limits to 1 item per order? J

    private  String order_id;
    private  String order_item_name;
    private String order_item_qty;
    private float order_subtotal;
    private float order_tax;
    private float order_shipping_charges;
    private float order_total;

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_item_name(String order_item_name) {
        this.order_item_name = order_item_name;
    }

    public void setOrder_item_qty(String order_item_qty) {
        this.order_item_qty = order_item_qty;
    }

    public void setOrder_subtotal(float order_subtotal) {
        this.order_subtotal = order_subtotal;
    }

    public void setOrder_tax(float order_tax) {
        this.order_tax = order_tax;
    }

    public void setOrder_shipping_charges(float order_shipping_charges) {
        this.order_shipping_charges = order_shipping_charges;
    }

    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public String getOrder_item_qty() {
        return order_item_qty;
    }

    public float getOrder_subtotal() {
        return order_subtotal;
    }

    public float getOrder_tax() {
        return order_tax;
    }

    public float getOrder_shipping_charges() {
        return order_shipping_charges;
    }

    public float getOrder_total() {
        return order_total;
    }

    public String getOrder_id() {
        return order_id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "order_item_name='" + order_item_name + '\'' +

                ", order_item_qty='" + order_item_qty + '\'' +
                ", order_total='" + order_total + '\'' +
                ", order_id='" + order_id + '\'' +
                " order_subtotal='" + order_subtotal + '\'' +
                ", order_tax='" + order_tax + '\'' +
                ", order_shipping_charges='" + order_shipping_charges + '\'' +
                '}';

    }
}
