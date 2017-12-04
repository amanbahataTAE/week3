package com.example.aman1.parkingapp.location;

import com.example.aman1.parkingapp.views.ui.base.MvpPresenter;

/**
 * Created by aman1 on 04/12/2017.
 */

public interface LocationMvpPresenter <V extends LocationMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}

