package com.example.admin.test;

import android.util.Log;

/**
 * Created by admin on 02.03.2015.
 */
public class Office {
    // boolean read, write;
    private String name;
    private String address;

    public Office(){
    }

    public String getName(){
        return  name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAdress(){
        return address;
    }
    public void setAdress(String address) {
        this.address = address;
    }
    /*public boolean getRead(){
         return read;
     }
    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean getWrite(){
        return write;
    }
    public void setWrite(boolean write) {
        this.write = write;
    }*/
}
