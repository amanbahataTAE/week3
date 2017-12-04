package com.example.aman1.parkingapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by aman1 on 03/12/2017.
 */

public class MyParkingApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();


        /**
         * Initializes Realm
         */

        Realm.init(getApplicationContext());


        // Builder pattern used
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();


        Realm.setDefaultConfiguration(configuration);

    }
}
