package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    private static final String TAG = "ResponseModel";

    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public boolean status;

    public ResponseModel() {
    }

}
