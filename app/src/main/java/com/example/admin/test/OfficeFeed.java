package com.example.admin.test;

import java.util.List;

/**
 * Created by admin on 04.03.2015.
 */
public class OfficeFeed {
    private List<Office> results;

    public OfficeFeed(){}

    public List<Office> getFeed() {
        return this.results;
    }

    public void setFeed(List<Office> results) {
        this.results = results;
    }
}
