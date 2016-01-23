package org.packathon.android.services;

import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.packathon.android.events.FeedEvent;
import org.packathon.android.events.FeedsEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.models.FeedListModel;
import org.packathon.android.models.FeedModel;
import org.packathon.android.rest.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EBean
public class FeedService {

    private static final String TAG = "FeedService";

    @Bean RestClient api;

    @Background
    public void getFeeds() {
        api.restService.getFeeds(
                1000,
                new Callback<FeedModel>() {
                    @Override
                    public void success(FeedModel feedModel, Response response) {
                        BusHelper.BUS.post(new FeedsEvent(feedModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new FeedsEvent(error));
                    }
                }
        );
    }

    @Background
    public void getFeed(int id) {
        api.restService.getFeed(
                id,
                new Callback<FeedListModel>() {
                    @Override
                    public void success(FeedListModel feedListModel, Response response) {
                        BusHelper.BUS.post(new FeedEvent(feedListModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new FeedEvent(error));
                    }
                }
        );
    }

}
