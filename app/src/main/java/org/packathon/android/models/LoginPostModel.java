package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class LoginPostModel {

    private static final String TAG = "LoginPostModel";

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    public LoginPostModel() {
    }

    public LoginPostModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
