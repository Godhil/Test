package com.example.admin.test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin on 02.03.2015.
 */
public class OfficeInfo {
    List<Office> offices = new LinkedList<>();


    public List<Office> getOffices() {
        return new LinkedList<>(offices);
    }
    public void addOffice(Office office){
        this.offices.add(office);
    }
}


