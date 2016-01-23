package org.packathon.android.services;

import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.packathon.android.events.VoteEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.models.ResponseModel;
import org.packathon.android.rest.RestClient;
import org.packathon.android.utils.ObjectCache;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EBean
public class VoteService {

    private static final String TAG = "VoteService";

    @Bean RestClient api;
    @Bean UserService userService;
    @Bean ObjectCache objectCache;

    @Background
    public void vote(int projectId) {
        api.restService.vote(
                "Token " + userService.getToken(),
                projectId,
                new Callback<ResponseModel>() {
                    @Override
                    public void success(ResponseModel responseModel, Response response) {
                        BusHelper.BUS.post(new VoteEvent(responseModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new VoteEvent(error));
                    }
                }
        );
    }

    public void saveVote(int projectId) {
        objectCache.putObject("VOTED_PROJECT", projectId);
        objectCache.commit();
    }

}
