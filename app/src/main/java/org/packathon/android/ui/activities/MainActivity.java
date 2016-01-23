package org.packathon.android.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.packathon.android.R;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.services.FragmentService;
import org.packathon.android.services.UserService;
import org.packathon.android.ui.events.SwitchFragment;
import org.packathon.android.ui.fragments.FragmentHome_;
import org.packathon.android.ui.fragments.FragmentLogin_;
import org.packathon.android.utils.AlertUtil;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Bean FragmentService fragmentService;
    @Bean AlertUtil alertUtil;
    @Bean UserService userService;

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;

    @ViewById(R.id.menu)
    protected ListView drawerList;

    private ActionBarDrawerToggle drawerToggle;
    private String title;
    private ActionBar actionBar;
    private String[] menuRes;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.drawer));
        toolbar.invalidate();

        getDrawer();
    }

    public void getDrawer() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content, new FragmentHome_());
        ft.commit();

        title = getString(R.string.app_name);
        actionBar.setTitle(title);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.drawer,
                R.string.drawer_open,
                R.string.drawer_closed
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getDrawerMenu();
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        getDrawerMenu();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void getDrawerMenu() {
        menuRes = getResources().getStringArray(R.array.drawer_menu);
        List<String> menuItems = new ArrayList<>();
        for (String menu : menuRes) {
            menuItems.add(menu);
        }
        if (userService.isLoggedIn()) {
            menuItems.add(getString(R.string.label_logout));
        }
        menuRes = menuItems.toArray(new String[menuItems.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getBaseContext(),
                R.layout.menu_item,
                menuRes
        );
        drawerList.setAdapter(adapter);
    }

    @ItemClick(R.id.menu)
    protected void onDrawerItemClick(int position) {
        switch (position) {
            case 0:
                BusHelper.BUS.post(new SwitchFragment(getSupportFragmentManager(), new FragmentHome_(), true));
                break;
            case 1:
                BusHelper.BUS.post(new SwitchFragment(getSupportFragmentManager(), new FragmentLogin_(), true));
                break;
            case 2:
                userService.logout();
                alertUtil.showToast(getString(R.string.msg_logout_success));
                BusHelper.BUS.post(new SwitchFragment(getSupportFragmentManager(), new FragmentHome_(), false));
                break;
        }

        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public void onBackPressed() {
        switch (fragmentService.getFragmentType()) {
            case HOME:
                finish();
                return;
            case VOTE:
                BusHelper.BUS.post(new SwitchFragment(getSupportFragmentManager(), new FragmentHome_(), true));
                return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
