package org.packathon.android.rest;

import com.squareup.okhttp.OkHttpClient;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

@EBean
public class RestClient {

    private static final String TAG = "RestClient";

    private static final String BASE_URL = "https://packathon.pyninjas.com/api";
    public RestService restService;

    @AfterInject
    public void afterInject() {
        OkHttpClient client = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(client))
                .build();

        restService = restAdapter.create(RestService.class);
    }

}
