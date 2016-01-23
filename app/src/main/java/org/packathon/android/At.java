package org.packathon.android;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import org.androidannotations.annotations.EApplication;

@EApplication
public class At extends Application {

    public At() {
        super();
    }

    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "", "");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
