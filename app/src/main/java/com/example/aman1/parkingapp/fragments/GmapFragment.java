package com.example.aman1.parkingapp.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman1.parkingapp.R;
import com.example.aman1.parkingapp.data.AppDataManager;
import com.example.aman1.parkingapp.data.network.model.LocationVO;
import com.example.aman1.parkingapp.realm.RealmController;
import com.example.aman1.parkingapp.realm.RealmReservation;
import com.example.aman1.parkingapp.data.network.service.AllLocationApi;
import com.example.aman1.parkingapp.data.network.service.ServerConnection;
import com.example.aman1.parkingapp.presenter.GetLocationPresenter;
import com.example.aman1.parkingapp.views.ui.utils.rx.AppSchedulerProvider;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

/**
 * Created by aman1 on 01/12/2017.
 */

public class GmapFragment extends Fragment implements OnMapReadyCallback {

    private GetLocationPresenter presenter;
    private GoogleMap mGoogleMap;
    private AllLocationApi allLocationApi;
    private RealmController realmController;
    private Button reserveButton;

    public static GmapFragment newInstance() {

        Bundle args = new Bundle();

        GmapFragment fragment = new GmapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allLocationApi = ServerConnection.getServerConnection();

        presenter = new GetLocationPresenter(this, allLocationApi);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gmaps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (isNetworkAvailable()) {
            presenter.getAllLocations();
            zoomToLocationArea();
        }else{
            Toast.makeText(getContext(), "Please check your connection", Toast.LENGTH_LONG).show();
        }

        initializeRealm();

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("TAG", (String) marker.getTag());
                presenter.getLocationDetail(Integer.parseInt((String) marker.getTag()));

                return false;
            }
        });



    }

    /**
     * Displays the list of parking spaces
     * @param locationVOS
     */

    public void showList(List<LocationVO> locationVOS) {

        for (final LocationVO locationVO : locationVOS) {

            MarkerOptions marker = new MarkerOptions()
                    .position(new LatLng(stringToDouble(locationVO.getLat()), stringToDouble(locationVO.getLng())))
                    .anchor(0.0f, 1.0f)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            (Boolean.parseBoolean(
                                    locationVO.getIs_reserved()))? BitmapDescriptorFactory.HUE_RED : BitmapDescriptorFactory.HUE_AZURE));

            mGoogleMap.addMarker(marker).setTag(locationVO.getId());

        }
    }


    /**
     * Displays the details of a single parking location
     * @param location
     */

    public void showLocationDetail(final LocationVO location) {

        Log.i("SINGLE", location.toString());

        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_window_layout, null);
                initializeTextViews(view, location);

                mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        reserveButton.setVisibility(View.GONE);

                        doReservation(location);
                        presenter.reserveLocation(Integer.parseInt(location.getId()));
                    }
                });

                return view;
            }
        });

    }

    /**
     * Converts the string to its double value
     * @param val
     * @return
     */

    private double stringToDouble(String val) {
        return Double.valueOf(val);
    }

    private void zoomToLocationArea(){
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(37.7749, -122.4194));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mGoogleMap.moveCamera(center);
        mGoogleMap.animateCamera(zoom);
    }

    /**
     * Converts the timestamp for the reservation time of the single location
     * @param time
     * @return
     * @throws ParseException
     */

    public String convertTime(String time) throws ParseException {

        time = time.replaceAll("T", " ")
                .replaceAll("Z", " ").trim()
                .split("\\.", 2)[0];


        Log.i("TRANSFORMED", time);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(time);
        String newDate = new SimpleDateFormat("HH:mm").format(date);

        return newDate;
    }

    /**
     * Initializes the view
     * @param view
     * @param location
     */

    private void initializeTextViews(View view, LocationVO location){


        TextView locationId = (TextView) view.findViewById(R.id.tv_location_id);
        TextView reserveInfo = (TextView) view.findViewById(R.id.tv_is_reserved);
        TextView minReserveTime = (TextView) view.findViewById(R.id.tv_min_booking_time);
        TextView maxReserveTime = (TextView) view.findViewById(R.id.tv_max_booking_time);
        TextView reservationCost = (TextView) view.findViewById(R.id.tv_reserve_cost);
        reserveButton = ((Button) view.findViewById(R.id.btn_reserve));
        reserveButton.setVisibility(View.GONE);

        locationId.setText("Location id: " + location.getName());

        String time = location.getReserved_until();


        try {
            String timeUntil = "";
            if (time != null) {
                timeUntil = convertTime(time).toString();
            }
            reserveInfo.setText(booleanParser(location.getIs_reserved())? "Location reserved until " + timeUntil : "Location available");
            if (!booleanParser(location.getIs_reserved())){
                reserveButton.setVisibility(View.VISIBLE);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        minReserveTime.setText(location.getMin_reserve_time_mins() + "min");
        maxReserveTime.setText(location.getMax_reserve_time_mins() + "min");
        reservationCost.setText(String.valueOf(location.getCost_per_minute()) + "$/min");

    }

    /**
     * Parses a string to its boolean value
     * @param string
     * @return
     */

    private Boolean booleanParser(String string){
        return Boolean.parseBoolean(string);
    }

    /**
     * Completes the reservation for the location
     * @param locationVO
     */

    public void doReservation(LocationVO locationVO) {
        Toast.makeText(getContext(), "Your reservation has been completed", Toast.LENGTH_LONG).show();
        storeToRealm(locationVO);
    }


    private void storeToRealm(LocationVO location) {
        RealmReservation realmReservation = new RealmReservation(
                location.getId(), location.getLat(), location.getLng(),location.getName(), String.valueOf(location.getCost_per_minute()),
                location.getMax_reserve_time_mins(), location.getMin_reserve_time_mins(), location.getIs_reserved(),
                location.getReserved_until());

        realmController.saveReservation(realmReservation);

    }

    public void initializeRealm(){
        Realm.init(getContext());
        realmController = new RealmController(Realm.getDefaultInstance());
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
