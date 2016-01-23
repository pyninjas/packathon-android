package org.packathon.android.events;

import org.packathon.android.models.MeModel;

import retrofit.RetrofitError;

public class MeEvent {

    private static final String TAG = "MeEvent";

    public MeModel meModel;
    public RetrofitError retrofitError;

    public MeEvent(Object object) {

        if (object.getClass() == MeModel.class) {
            this.meModel = (MeModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.meModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.meModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return meModel == null && retrofitError != null;
    }

}
