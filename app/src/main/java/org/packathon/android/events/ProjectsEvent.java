package org.packathon.android.events;

import org.packathon.android.models.ProjectModel;

import retrofit.RetrofitError;

public class ProjectsEvent {

    private static final String TAG = "ProjectsEvent";

    public ProjectModel projectModel;
    public RetrofitError retrofitError;

    public ProjectsEvent(Object object) {

        if (object.getClass() == ProjectModel.class) {
            this.projectModel = (ProjectModel) object;
            this.retrofitError = null;
        } else if (object.getClass() == RetrofitError.class) {
            this.projectModel = null;
            this.retrofitError = (RetrofitError) object;
        } else {
            this.projectModel = null;
            this.retrofitError = null;
        }

    }

    public boolean hasError() {
        return projectModel == null && retrofitError != null;
    }

}
