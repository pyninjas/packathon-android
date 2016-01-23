package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamModel {

    private static final String TAG = "TeamModel";

    @SerializedName("count")
    public int count;

    @SerializedName("next")
    public String next;

    @SerializedName("previous")
    public String previous;

    @SerializedName("results")
    public List<TeamListModel> results;

    public TeamModel() {
    }

}
