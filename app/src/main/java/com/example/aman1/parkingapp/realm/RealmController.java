package com.example.aman1.parkingapp.realm;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by aman1 on 03/12/2017.
 */

public class RealmController {
    Realm realm;

    public RealmController(Realm realm) {
        this.realm = realm;
    }

    /**
     * Stores the reservation information to the database
     * @param realmReservation
     */
    public void saveReservation(final RealmReservation realmReservation){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmReservation);
            }
        });
    }


    /**
     * Returns the reservations list from the database
     */

    public ArrayList<RealmReservation> getReservationList(){
        ArrayList<RealmReservation> reservationList = new ArrayList<>();

        RealmResults<RealmReservation> realmTrackResults = realm.where(RealmReservation.class).findAll();

        for (RealmReservation reservation : realmTrackResults) {
            reservationList.add(reservation);
        }
        return reservationList;
    }
}
