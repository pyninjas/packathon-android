package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamListModel {

    private static final String TAG = "TeamListModel";

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("project")
    public String project;

    @SerializedName("users")
    public List<UserListModel> users;

    public TeamListModel() {
    }

}
