package org.packathon.android.receivers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;
import org.packathon.android.R;

public class PushReceiver extends ParsePushBroadcastReceiver {

    protected Intent parseIntent;
    public static String description;

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        if (intent == null)
            return;

        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            description = json.optString("alert", null);
            parseIntent = intent;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getSmallIconId(Context context, Intent intent) {
        return R.mipmap.ic_launcher;
    }

    @Override
    protected Bitmap getLargeIcon(Context context, Intent intent) {
        return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        intent.putExtra("push", true);

        if (description != null)
            intent.putExtra("description", description);

        super.onPushOpen(context, intent);
    }

}
