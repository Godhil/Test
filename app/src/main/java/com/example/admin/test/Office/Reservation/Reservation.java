package com.example.admin.test.Office.Reservation;

/**
 * Created by admin on 10.03.2015.
 */
public class Reservation {
    private String customerName;
    private String customerPhone;
    private String objectId;

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
