package org.packathon.android.services;

import android.content.Context;
import android.util.Log;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.packathon.android.events.LoginEvent;
import org.packathon.android.events.MeEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.models.LoginPostModel;
import org.packathon.android.models.LoginResponseModel;
import org.packathon.android.models.MeModel;
import org.packathon.android.rest.RestClient;
import org.packathon.android.utils.ObjectCache;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EBean
public class UserService {

    private static final String TAG = "UserService";
    public static final String TOKEN_KEY = "auth_key";

    @RootContext Context context;

    @Bean RestClient api;
    @Bean ObjectCache objectCache;

    @AfterInject
    public void afterInject() {
    }

    @Background
    public void login(String email, String password) {
        api.restService.login(
                "application/json",
                new LoginPostModel(email, password),
                new Callback<LoginResponseModel>() {
                    @Override
                    public void success(LoginResponseModel loginResponseModel, Response response) {
                        saveLogin(loginResponseModel.key);
                        BusHelper.BUS.post(new LoginEvent(loginResponseModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "Login Error: " + error.getLocalizedMessage());
                        BusHelper.BUS.post(new LoginEvent(error));
                    }
                }
        );
    }

    @Background
    public void whoAmI() {
        api.restService.me(
                "Token " + getToken(),
                new Callback<MeModel>() {
                    @Override
                    public void success(MeModel meModel, Response response) {
                        objectCache.putObject("VOTED_PROJECT", meModel.voted_project);
                        objectCache.commit();
                        BusHelper.BUS.post(new MeEvent(meModel));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        BusHelper.BUS.post(new MeEvent(error));
                    }
                }
        );
    }

    public void saveLogin(String key) {
        objectCache.putObject(TOKEN_KEY, key);
        objectCache.commit();
    }

    public void logout() {
        objectCache.removeObject(TOKEN_KEY);
        objectCache.commit();
    }

    public String getToken() {
        return objectCache.getObject(TOKEN_KEY, String.class);
    }

    public boolean isLoggedIn() {
        return objectCache.getObject(TOKEN_KEY, String.class) != null;
    }

}
