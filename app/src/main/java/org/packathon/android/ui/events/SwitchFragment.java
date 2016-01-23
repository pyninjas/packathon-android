package org.packathon.android.ui.events;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.packathon.android.R;
import org.packathon.android.ui.fragments.BaseFragment;

public class SwitchFragment {

    private String fragmentTag;

    public SwitchFragment(FragmentManager fragmentManager, BaseFragment fragment, boolean isAnimated) {
        fragmentTag = fragment.getClass().getName();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (isAnimated)
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        ft.replace(R.id.content, fragment);
        ft.addToBackStack(fragmentTag);
        ft.commit();
    }

}
