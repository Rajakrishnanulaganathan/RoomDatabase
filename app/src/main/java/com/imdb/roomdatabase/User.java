package com.imdb.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @NonNull
    @PrimaryKey
    public String mUserName;

    public  String mUserPhone;
    public  String mUserEmail;
    public   String mUserCity;

    public User(String mUserName, String mUserEmail, String mUserPhone, String mUserCity) {
        this.mUserName = mUserName;
        this.mUserPhone = mUserPhone;
        this.mUserEmail = mUserEmail;
        this.mUserCity = mUserCity;

    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserPhone() {
        return mUserPhone;
    }

    public void setmUserPhone(String mUserPhone) {
        this.mUserPhone = mUserPhone;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public void setmUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public String getmUserCity() {
        return mUserCity;
    }

    public void setmUserCity(String mUserCity) {
        this.mUserCity = mUserCity;
    }


}
