package org.packathon.android.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.packathon.android.R;

@EBean
public class AlertUtil {

    private static final String TAG = "AlertUtil";

    @RootContext Context context;
    public ProgressDialog progressDialog;

    @AfterInject
    public void afterInject() {
        progressDialog = new ProgressDialog(context);
    }

    public void showLoadingDialog() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.msg_please_wait));
        progressDialog.show();
    }

    public void showMessage(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(
                        context.getString(R.string.label_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );
        alert.create().show();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        context.getString(R.string.label_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );
        alert.create().show();
    }

    public void dissmissDialog() {
        progressDialog.dismiss();
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
