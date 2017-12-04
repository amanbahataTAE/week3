package com.example.aman1.parkingapp.data;


import com.example.aman1.parkingapp.data.network.model.LocationVO;
import com.example.aman1.parkingapp.data.network.service.ApiHelper;
import com.example.aman1.parkingapp.data.network.service.AppApiHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by aman1 on 26/11/2017.
 */

public class AppDataManager implements IDataManager {

    private ApiHelper apiHelper;

    public AppDataManager() {
        apiHelper = new AppApiHelper();
    }

    @Override
    public Observable<List<LocationVO>> getAllLocations() {
        return apiHelper.getAllLocations();
    }

    @Override
    public Observable<LocationVO> getLocationDetail(int locationId) {
        return apiHelper.getLocationDetail(locationId);
    }

    @Override
    public Observable<LocationVO> reserveLocation(int locationId) {
        return apiHelper.reserveLocation(locationId);
    }
}
