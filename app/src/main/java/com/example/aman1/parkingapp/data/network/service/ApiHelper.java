package com.example.aman1.parkingapp.data.network.service;

import com.example.aman1.parkingapp.data.network.model.LocationVO;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by aman1 on 04/12/2017.
 */

public interface ApiHelper {

    Observable<List<LocationVO>> getAllLocations();

    Observable<LocationVO> getLocationDetail(int locationId);

    Observable<LocationVO> reserveLocation(int locationId);
}
