package org.packathon.android.events;

import org.packathon.android.models.FeedModel;

import retrofit.RetrofitError;

public class FeedsEvent {

    private static final String TAG = "FeedsEvent";

    public FeedModel feedModel;
    public RetrofitError retrofitError;

    public FeedsEvent(Object object) {

        if (object.getClass() == FeedModel.class) {
            this.feedModel = (FeedModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.feedModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.feedModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return feedModel == null && retrofitError != null;
    }

}
