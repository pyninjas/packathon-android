package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class ProjectListModel {

    private static final String TAG = "ProjectListModel";

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("git")
    public String git;

    @SerializedName("website")
    public String website;

    @SerializedName("team")
    public ProjectTeamModel team;

    public ProjectListModel() {
    }

}
