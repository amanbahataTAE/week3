package com.example.aman1.parkingapp.location;

import com.example.aman1.parkingapp.data.network.model.LocationVO;
import com.example.aman1.parkingapp.views.ui.base.MvpView;

import java.util.List;

/**
 * Created by aman1 on 04/12/2017.
 */

public interface LocationMvpView extends MvpView {

    void onFetchDataSuccess(List<LocationVO> locationModel);
    void onFetchDataError(String message);
}
