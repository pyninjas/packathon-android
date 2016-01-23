package org.packathon.android.events;

import org.packathon.android.models.ResponseModel;

import retrofit.RetrofitError;

public class VoteEvent {

    private static final String TAG = "VoteEvent";

    public ResponseModel responseModel;
    public RetrofitError retrofitError;

    public VoteEvent(Object object) {

        if (object.getClass() == ResponseModel.class) {
            this.responseModel = (ResponseModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.responseModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.responseModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return responseModel == null && retrofitError != null;
    }

    public ResponseModel getError() {
        return (ResponseModel) retrofitError.getBodyAs(ResponseModel.class);
    }

}
