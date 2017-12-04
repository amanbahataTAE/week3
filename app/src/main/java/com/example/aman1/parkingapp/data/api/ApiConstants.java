package com.example.aman1.parkingapp.data.api;

/**
 * Created by aman1 on 02/12/2017.
 */

public class ApiConstants {

    public static final String BASE_URL = "http://ridecellparking.herokuapp.com/api/v1/parkinglocations/";
    public static final String LOCATION_DETAIL = BASE_URL + "{id}";
    public static final String RESERVE_LOCATION = LOCATION_DETAIL + "/reserve";
}
