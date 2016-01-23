package org.packathon.android.events;

import org.packathon.android.models.LoginResponseModel;

import retrofit.RetrofitError;

public class LoginEvent {

    private static final String TAG = "FeedsEvent";

    public LoginResponseModel loginResponseModel;
    public RetrofitError retrofitError;

    public LoginEvent(Object object) {

        if (object.getClass() == LoginResponseModel.class) {
            this.loginResponseModel = (LoginResponseModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.loginResponseModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.loginResponseModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return loginResponseModel == null && retrofitError != null;
    }

}
