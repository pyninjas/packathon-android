package org.packathon.android.models;

import com.google.gson.annotations.SerializedName;

public class FeedListModel {

    private static final String TAG = "FeedListModel";

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("created_at")
    public String created_at;

    public FeedListModel() {
    }

}
