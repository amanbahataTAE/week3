package com.example.aman1.parkingapp.realm;

import io.realm.RealmObject;

/**
 * Created by aman1 on 03/12/2017.
 */

public class RealmReservation extends RealmObject {

        private String mId;
        private String mLat;
        private String mLng;
        private String mName;
        private String mCostPerMinute;
        private String mMaxReserveTimeMins;
        private String mMinReserveTimeMins;
        private String mIsReserved;
        private String mReservedUntil;

    public RealmReservation(String mId, String mLat, String mLng, String mName,
                            String mCostPerMinute, String mMaxReserveTimeMins,
                            String mMinReserveTimeMins, String mIsReserved, String mReservedUntil) {
        this.mId = mId;
        this.mLat = mLat;
        this.mLng = mLng;
        this.mName = mName;
        this.mCostPerMinute = mCostPerMinute;
        this.mMaxReserveTimeMins = mMaxReserveTimeMins;
        this.mMinReserveTimeMins = mMinReserveTimeMins;
        this.mIsReserved = mIsReserved;
        this.mReservedUntil = mReservedUntil;
    }

    public RealmReservation() {
    }


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmLat() {
        return mLat;
    }

    public void setmLat(String mLat) {
        this.mLat = mLat;
    }

    public String getmLng() {
        return mLng;
    }

    public void setmLng(String mLng) {
        this.mLng = mLng;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCostPerMinute() {
        return mCostPerMinute;
    }

    public void setmCostPerMinute(String mCostPerMinute) {
        this.mCostPerMinute = mCostPerMinute;
    }

    public String getmMaxReserveTimeMins() {
        return mMaxReserveTimeMins;
    }

    public void setmMaxReserveTimeMins(String mMaxReserveTimeMins) {
        this.mMaxReserveTimeMins = mMaxReserveTimeMins;
    }

    public String getmMinReserveTimeMins() {
        return mMinReserveTimeMins;
    }

    public void setmMinReserveTimeMins(String mMinReserveTimeMins) {
        this.mMinReserveTimeMins = mMinReserveTimeMins;
    }

    public String getmIsReserved() {
        return mIsReserved;
    }

    public void setmIsReserved(String mIsReserved) {
        this.mIsReserved = mIsReserved;
    }

    public String getmReservedUntil() {
        return mReservedUntil;
    }

    public void setmReservedUntil(String mReservedUntil) {
        this.mReservedUntil = mReservedUntil;
    }
}
