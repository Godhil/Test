package com.example.admin.test.Office.Place;


/**
 * Created by admin on 09.03.2015.
 */
public class Place {

    private int number;
    private int capacity;
    private String objectId;
    private Office office;


    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }


    public static class Office{
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
    }

}
