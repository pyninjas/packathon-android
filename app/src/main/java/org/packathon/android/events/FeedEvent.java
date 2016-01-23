package org.packathon.android.events;

import org.packathon.android.models.FeedListModel;

import retrofit.RetrofitError;

public class FeedEvent {

    private static final String TAG = "FeedEvent";

    public FeedListModel feedListModel;
    public RetrofitError retrofitError;

    public FeedEvent(Object object) {

        if (object.getClass() == FeedListModel.class) {
            this.feedListModel = (FeedListModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.feedListModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.feedListModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return feedListModel == null && retrofitError != null;
    }

}
