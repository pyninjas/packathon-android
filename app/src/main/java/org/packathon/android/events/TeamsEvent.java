package org.packathon.android.events;

import org.packathon.android.models.TeamModel;

import retrofit.RetrofitError;

public class TeamsEvent {

    private static final String TAG = "TeamsEvent";

    public TeamModel teamModel;
    public RetrofitError retrofitError;

    public TeamsEvent(Object object) {

        if (object.getClass() == TeamModel.class) {
            this.teamModel = (TeamModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.teamModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.teamModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return teamModel == null && retrofitError != null;
    }

}
