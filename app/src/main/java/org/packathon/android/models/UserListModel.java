package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class UserListModel {

    private static final String TAG = "UserListModel";

    @SerializedName("id")
    public int id;

    @SerializedName("username")
    public String username;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("team")
    public String team;

    @SerializedName("website")
    public String website;

    @SerializedName("git")
    public String git;

    @SerializedName("twitter")
    public String twitter;

    @SerializedName("email")
    public String email;

    @SerializedName("photo")
    public String photo;

    @SerializedName("url")
    public String url;

    @SerializedName("is_staff")
    public boolean is_staff;

    public UserListModel() {
    }

}
