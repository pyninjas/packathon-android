package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {

    private static final String TAG = "UserModel";

    @SerializedName("count")
    public int count;

    @SerializedName("next")
    public String next;

    @SerializedName("previous")
    public String previous;

    @SerializedName("results")
    public List<UserListModel> results;

    public UserModel() {
    }

}
