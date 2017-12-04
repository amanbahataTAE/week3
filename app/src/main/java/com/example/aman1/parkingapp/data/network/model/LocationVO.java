package com.example.aman1.parkingapp.data.network.model;

/**
 * Created by aman1 on 01/12/2017.
 */

public class LocationVO {

    private double cost_per_minute;
    private String id;
    private String reserved_until;
    private String min_reserve_time_mins;
    private String name;
    private String lng;
    private String is_reserved;
    private String max_reserve_time_mins;
    private String lat;

    public double getCost_per_minute() {
        return cost_per_minute;
    }

    public String getId() {
        return id;
    }

    public String getReserved_until() {
        return reserved_until;
    }

    public String getMin_reserve_time_mins() {
        return min_reserve_time_mins;
    }


    public String getName() {
        return name;
    }

    public String getLng() {
        return lng;
    }

    public String getIs_reserved() {
        return is_reserved;
    }


    public String getMax_reserve_time_mins() {
        return max_reserve_time_mins;
    }

    public String getLat() {
        return lat;
    }


    @Override
    public String toString() {
        return "ClassPojo [cost_per_minute = " + cost_per_minute + ", id = " + id + ", reserved_until = " + reserved_until + ", min_reserve_time_mins = " + min_reserve_time_mins + ", name = " + name + ", lng = " + lng + ", is_reserved = " + is_reserved + ", max_reserve_time_mins = " + max_reserve_time_mins + ", lat = " + lat + "]";
    }
}
