package com.example.aman1.parkingapp.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aman1.parkingapp.R;
import com.example.aman1.parkingapp.data.realm.RealmController;
import com.example.aman1.parkingapp.data.realm.RealmReservation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aman1 on 03/12/2017.
 */

public class ReservationAdaptor extends RecyclerView.Adapter<ReservationAdaptor.ViewHolder>  {


    private List<RealmReservation> realmReservationList;
    private int mRowReservation;
    private Context mApplicationContext;


    public ReservationAdaptor(List<RealmReservation> realmReservationList, int mRowReservation,
                              Context mApplicationContext) {

        this.realmReservationList = realmReservationList;
        this.mRowReservation = mRowReservation;
        this.mApplicationContext = mApplicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mApplicationContext).inflate(mRowReservation, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mReservationName.setText(realmReservationList.get(position).getmName());
        holder.mReservedUntil.setText(realmReservationList.get(position).getmReservedUntil());

    }

    @Override
    public int getItemCount() {
        return realmReservationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_location_name) TextView mReservationName;
        @BindView(R.id.reserved_until) TextView mReservedUntil;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
