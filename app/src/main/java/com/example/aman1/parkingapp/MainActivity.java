package com.example.aman1.parkingapp;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.aman1.parkingapp.fragments.GmapFragment;
import com.example.aman1.parkingapp.fragments.ReservationsFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final Map<Integer, Fragment> mFragmentMap = new HashMap<>();

    private TextView mTextMessage;
    private FragmentManager mFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                       mFragmentManager.beginTransaction()
                               .replace(R.id.fragment_container, mFragmentMap.get(0))
                               .commit();
                       return true;

                case R.id.navigation_reservation:
                        mFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, mFragmentMap.get(1))
                                .commit();
                        return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentMap.put(0, GmapFragment.newInstance());
        mFragmentMap.put(1, new ReservationsFragment());

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null){
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new GmapFragment())
                    .commit();
        }
    }

}
