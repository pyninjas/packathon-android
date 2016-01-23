package org.packathon.android.services;

import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.packathon.android.events.ProjectEvent;
import org.packathon.android.events.ProjectsEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.models.ProjectListModel;
import org.packathon.android.models.ProjectModel;
import org.packathon.android.rest.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EBean
public class ProjectService {

    private static final String TAG = "ProjectService";

    @Bean RestClient api;

    @Background
    public void getProjects() {
        api.restService.getProjects(
                1000,
                new Callback<ProjectModel>() {
                    @Override
                    public void success(ProjectModel projectModel, Response response) {
                        BusHelper.BUS.post(new ProjectsEvent(projectModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new ProjectsEvent(error));
                    }
                }
        );
    }

    @Background
    public void getProject(int id) {
        api.restService.getProject(
                id,
                new Callback<ProjectListModel>() {
                    @Override
                    public void success(ProjectListModel projectListModel, Response response) {
                        BusHelper.BUS.post(new ProjectEvent(projectListModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new ProjectEvent(error));
                    }
                }
        );
    }

}
