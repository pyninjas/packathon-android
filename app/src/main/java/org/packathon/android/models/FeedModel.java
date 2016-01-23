package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedModel {

    private static final String TAG = "FeedModel";

    @SerializedName("count")
    public int count;

    @SerializedName("next")
    public String next;

    @SerializedName("previous")
    public String previous;

    @SerializedName("results")
    public List<FeedListModel> results;

    public FeedModel() {
    }

}
