package org.packathon.android.services;

import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.packathon.android.events.TeamEvent;
import org.packathon.android.events.TeamsEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.models.TeamListModel;
import org.packathon.android.models.TeamModel;
import org.packathon.android.rest.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EBean
public class TeamService {

    private static final String TAG = "TeamService";

    @Bean RestClient api;

    @Background
    public void getTeams() {
        api.restService.getTeams(
                1000,
                new Callback<TeamModel>() {
                    @Override
                    public void success(TeamModel teamModel, Response response) {
                        BusHelper.BUS.post(new TeamsEvent(teamModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new TeamsEvent(error));
                    }
                }
        );
    }

    @Background
    public void getTeam(int id) {
        api.restService.getTeam(
                id,
                new Callback<TeamListModel>() {
                    @Override
                    public void success(TeamListModel teamListModel, Response response) {
                        BusHelper.BUS.post(new TeamEvent(teamListModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new TeamEvent(error));
                    }
                }
        );
    }

}
