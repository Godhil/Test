package com.example.admin.test.Office.Reservation;

import java.util.List;

/**
 * Created by admin on 10.03.2015.
 */
public class ReservationFeed {
    private List<Reservation> results;
    public ReservationFeed(){}

    public List<Reservation> getResults() {
        return results;
    }

    public void setResults(List<Reservation> results) {
        this.results = results;
    }
}
