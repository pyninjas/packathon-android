package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    private static final String TAG = "LoginResponseModel";

    @SerializedName("key")
    public String key;

    public LoginResponseModel() {
    }

    public LoginResponseModel(String key) {
        this.key = key;
    }

}
