package com.egen.Model;


import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import java.util.UUID;


@Entity
public class Shipping {

    //e. supports more shipping/delivery methods: In-store pickup, curbside delivery,
    //	ship to home, 3rd party delivery etc.

    @Id
    @Column(name="Shipping_Details")
    private String shippingId;

    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingState;
    private String shippingZip;

    public Shipping(String shippingAddress1, String shippingAddress2, String shippingCity, String shippingState, String shippingZip) {
        this.shippingId= UUID.randomUUID().toString(); // a randon 32 bit UUID is generated
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.shippingCity = shippingCity;
        this.shippingState = shippingState;
        this.shippingZip = shippingZip;
    }

    public Shipping() {
        this.shippingId= UUID.randomUUID().toString();
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public void setShippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
    }

    public String getShippingId() {
        return shippingId;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public String getShippingZip() {
        return shippingZip;
    }

    @Override
    public String toString() {
        return "ShippingDetails {" +
                "shippingtId='" +  shippingId + '\'' + "," +
                "ShippingAddress1='" +shippingAddress1+ '\'' +
                "ShippingAddress2='" +shippingAddress2+ '\'' +
                "ShippingCity='" +shippingCity+ '\'' +
                "ShippingState='" +shippingState+ '\'' +
                "ShippingZip='" +shippingZip+ '\'' +
                '}';
    }

    enum Shipping_Type {
        INSTORE ,CURBSIDE, SHIP_HOME,OTHERPARTY ;

    }
}




