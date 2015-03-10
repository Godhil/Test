package com.example.admin.test.Office.Place;


import com.google.gson.annotations.SerializedName;

import java.security.Key;

/**
 * Created by admin on 10.03.2015.
 */
public class CreateReservation {
    final String customerName;
    final String customerPhone;
    final Place place;

    CreateReservation(String customerName, String customerPhone, Place place){
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.place = place;
    }

    public static class Place {
        @SerializedName("__type")
        private String type;
        private String className;
        private String objectId;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
