package org.packathon.android.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.packathon.android.enums.FragmentType;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.services.FragmentService;

@EFragment
public class BaseFragment extends Fragment {

    public static String TAG = "BaseFragment";

    @Bean FragmentService fragmentService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusHelper.BUS.register(this);
        TAG = getClass().getSimpleName();
    }

    public void setFragmentType(FragmentType fragmentType) {
        fragmentService.setFragmentType(fragmentType);
    }

    @Override
    public void onDestroy() {
        try {
            BusHelper.BUS.unregister(this);
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        super.onDestroy();
    }

    public void setTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(title);
    }

}
