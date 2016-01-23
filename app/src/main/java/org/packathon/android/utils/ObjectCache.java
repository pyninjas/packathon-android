package org.packathon.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean(scope = EBean.Scope.Singleton)
public class ObjectCache {

    @RootContext Context context;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static Gson GSON = new Gson();
    private static final String LOG_TAG = "ObjectCache";
    private static final String PREFERENCES_NAME = "org.packathon.android";
    private static final int    PREFERENCES_MODE = Activity.MODE_PRIVATE;

    @AfterInject
    public void afterInject() {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, PREFERENCES_MODE);
        editor = preferences.edit();
    }

    public void putObject(String key, Object object) {
        if (object == null) {
            Log.d(LOG_TAG, "putObject/Object is null.");
        }
        if (key.isEmpty() || key == null) {
            Log.d(LOG_TAG, "putObject/Key is empty.");
        }
        editor.putString(key, GSON.toJson(object));
    }

    public void commit() {
        editor.commit();
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = preferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void removeObject(String key) {
        editor.remove(key);
    }


}
