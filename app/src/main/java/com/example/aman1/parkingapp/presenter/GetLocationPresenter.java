package com.example.aman1.parkingapp.presenter;

import com.example.aman1.parkingapp.data.IDataManager;
import com.example.aman1.parkingapp.data.network.model.LocationVO;
import com.example.aman1.parkingapp.fragments.GmapFragment;
import com.example.aman1.parkingapp.data.network.service.AllLocationApi;
import com.example.aman1.parkingapp.location.LocationMvpPresenter;
import com.example.aman1.parkingapp.location.LocationMvpView;
import com.example.aman1.parkingapp.views.ui.base.BasePresenter;
import com.example.aman1.parkingapp.views.ui.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aman1 on 01/12/2017.
 */

public class GetLocationPresenter{

    private final GmapFragment fragment;
    private final AllLocationApi allLocationApi;

    public GetLocationPresenter( GmapFragment fragment,AllLocationApi allLocationApi) {
        this.fragment = fragment;
        this.allLocationApi = allLocationApi;
    }

//    public GetLocationPresenter(GmapFragment fragment, AllLocationApi allLocationApi) {
//        this.fragment = fragment;
//        this.allLocationApi = allLocationApi;
//    }

    public void  getAllLocations() {
        allLocationApi.getAllLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LocationVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LocationVO> locationVOS) {
                        fragment.showList(locationVOS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getLocationDetail(int locationId){
        allLocationApi.getLocationDetail(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationVO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationVO locationVO) {
                        fragment.showLocationDetail(locationVO);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void reserveLocation(int locationId){
        allLocationApi.reserveLocation(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationVO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationVO locationVO) {
                        fragment.doReservation(locationVO);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
