package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class ProjectTeamModel {

    private static final String TAG = "ProjectTeamModel";

    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public int id;

    public ProjectTeamModel() {
    }

}
