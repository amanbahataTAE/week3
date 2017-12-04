package com.example.aman1.parkingapp.data.network.service;

import com.example.aman1.parkingapp.data.api.ApiConstants;
import com.example.aman1.parkingapp.data.network.model.LocationVO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by aman1 on 01/12/2017.
 */

public interface AllLocationApi {

    @GET(ApiConstants.BASE_URL)
    Observable<List<LocationVO>> getAllLocations();

    @GET(ApiConstants.LOCATION_DETAIL)
    Observable<LocationVO> getLocationDetail(@Path("id") int locationId);

    @POST(ApiConstants.RESERVE_LOCATION)
    Observable<LocationVO> reserveLocation(@Path("id") int locationId);


}
