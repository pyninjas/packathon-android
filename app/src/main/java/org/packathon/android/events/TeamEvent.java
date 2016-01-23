package org.packathon.android.events;

import org.packathon.android.models.TeamListModel;

import retrofit.RetrofitError;

public class TeamEvent {

    private static final String TAG = "TeamEvent";

    public TeamListModel teamListModel;
    public RetrofitError retrofitError;

    public TeamEvent(Object object) {

        if (object.getClass() == TeamListModel.class) {
            this.teamListModel = (TeamListModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.teamListModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.teamListModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return teamListModel == null && retrofitError != null;
    }

}
