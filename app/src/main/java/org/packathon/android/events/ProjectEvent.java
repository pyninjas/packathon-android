package org.packathon.android.events;

import org.packathon.android.models.ProjectListModel;

import retrofit.RetrofitError;

public class ProjectEvent {

    private static final String TAG = "ProjectEvent";

    public ProjectListModel projectListModel;
    public RetrofitError retrofitError;

    public ProjectEvent(Object object) {

        if (object.getClass() == ProjectListModel.class) {
            this.projectListModel = (ProjectListModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.projectListModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.projectListModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return projectListModel == null && retrofitError != null;
    }

}
