package com.example.aman1.parkingapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aman1.parkingapp.R;
import com.example.aman1.parkingapp.realm.RealmController;
import com.example.aman1.parkingapp.realm.RealmReservation;


import java.util.List;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationsFragment extends Fragment {


    private RealmController realmController;
    private RecyclerView mReservationRecyclerView;

    public ReservationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRealm();

        List<RealmReservation> listOfReservations = realmController.getReservationList();

        initiliseRecyclerView(view, listOfReservations);
    }

    public void initializeRealm(){
        Realm.init(getContext());
        realmController = new RealmController(Realm.getDefaultInstance());
    }

    public void initiliseRecyclerView(View view, List<RealmReservation> reservationList) {

        mReservationRecyclerView = (RecyclerView) view.findViewById(R.id.rvReservations);
        mReservationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mReservationRecyclerView.setAdapter(new ReservationAdaptor(reservationList, R.layout.row_reservations, getContext()));
    }




}
