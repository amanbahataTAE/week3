package com.example.aman1.parkingapp.data.network.service;

import com.example.aman1.parkingapp.data.network.model.LocationVO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by aman1 on 04/12/2017.
 */

public class AppApiHelper implements ApiHelper {

    AllLocationApi allLocationApi;

    public AppApiHelper() {
        allLocationApi = ServerConnection.getServerConnection();
    }

    @Override
    public Observable<List<LocationVO>> getAllLocations() {
        return allLocationApi.getAllLocations();
    }

    @Override
    public Observable<LocationVO> getLocationDetail(int locationId) {
        return allLocationApi.getLocationDetail(locationId);
    }

    @Override
    public Observable<LocationVO> reserveLocation(int locationId) {
        return allLocationApi.reserveLocation(locationId);
    }
}
