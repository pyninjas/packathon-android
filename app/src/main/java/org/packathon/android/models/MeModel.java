package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class MeModel {

    private static final String TAG = "MeModel";

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

    @SerializedName("voted_project")
    public int voted_project;

    public MeModel() {
    }

}
