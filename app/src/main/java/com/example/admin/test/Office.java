package com.example.admin.test;

import android.util.Log;

/**
 * Created by admin on 02.03.2015.
 */
public class Office {
    // boolean read, write;
    private String name, adress;

    public Office(){
    }

    public Office(/*boolean read, boolean write, */String name, String adress){
        //this.read = read;
        //this.write = write;
        this.name = name;
        this.adress = adress;

    }

    public String getName(){
        return  name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAdress(){
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
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
