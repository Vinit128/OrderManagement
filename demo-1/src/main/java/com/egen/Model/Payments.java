package com.egen.Model;


import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import java.util.Date;
        import java.util.UUID;


@Entity
public class Payments{
    //   c. provides an ability to pay the order by accepting more than one payment
    // transactions. For example, pay for an order amount using two credit cards (split
    //   the total). Make some assumptions about the billing address/es.

    @Id
    @Column(name="Payments")
    private String order_id;

    private float order_total;

    private String order_payment_method;
    private Date order_payment_date;
    private String order_payment_confirmation_number;

    public Payments(String order_payment_method, Date order_payment_date, String order_payment_confirmation_number) {
        this.order_id= UUID.randomUUID().toString();
        this.order_payment_method = order_payment_method;
        this.order_payment_date = order_payment_date;
        this.order_payment_confirmation_number = order_payment_confirmation_number;
    }

    public Payments() {
        this.order_id= UUID.randomUUID().toString();
    }

    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_payment_method(String order_payment_method) {
        this.order_payment_method = order_payment_method;
    }

    public void setOrder_payment_date(Date order_payment_date) {
        this.order_payment_date = order_payment_date;
    }

    public void setOrder_payment_confirmation_number(String order_payment_confirmation_number) {
        this.order_payment_confirmation_number = order_payment_confirmation_number;
    }

    public float getOrder_total() {
        return order_total;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_payment_method() {
        return order_payment_method;
    }

    public Date getOrder_payment_date() {
        return order_payment_date;
    }

    public String getOrder_payment_confirmation_number() {
        return order_payment_confirmation_number;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "order_payment_method='" + order_payment_method + '\'' +
                ", order_payment_date='" + order_payment_date + '\'' +
                ", order_payment_confirmation_number='" + order_payment_confirmation_number + '\'' +
                ", order_total='" + order_total + '\'' +
                ", order_id='" + order_id + '\'' +
                '}';

    }

    enum Payment {
        AMAZONPAY, CREDIT_CARD, DEBIT_CARD, GOOGLEPAY, PAYPAL

    }
}



