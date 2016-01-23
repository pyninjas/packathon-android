package org.packathon.android.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.packathon.android.R;
import org.packathon.android.enums.FragmentType;
import org.packathon.android.events.FeedsEvent;
import org.packathon.android.models.FeedListModel;
import org.packathon.android.services.FeedService;
import org.packathon.android.ui.adapters.FeedAdapter;
import org.packathon.android.utils.AlertUtil;

import java.util.ArrayList;

@EFragment(R.layout.fragment_home)
public class FragmentHome extends BaseFragment {

    @Bean AlertUtil alertUtil;
    @Bean FeedService feedService;

    @ViewById(R.id.error_container)
    protected LinearLayout errorContainer;

    @ViewById(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.feed_list)
    protected ListView feedList;

    @AfterViews
    protected void setupViews() {
        setFragmentType(FragmentType.HOME);
        setTitle(getString(R.string.app_name));

        alertUtil.showLoadingDialog();
        feedService.getFeeds();
    }

    @Subscribe
    public void onGetFeeds(FeedsEvent result) {
        if (result.hasError()) {
            feedList.setVisibility(View.GONE);
            errorContainer.setVisibility(View.VISIBLE);
            stopLoadings();
            return;
        }

        feedList.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);

        FeedAdapter adapter = new FeedAdapter(
                getContext(),
                R.layout.item_feed,
                (ArrayList<FeedListModel>) result.feedModel.results
        );
        feedList.setAdapter(adapter);

        getSwipeRefreshLayout();

        stopLoadings();
    }

    private void getSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedService.getFeeds();
            }
        });
    }

    @Click(R.id.try_again)
    public void onTryAgainClicked() {
        alertUtil.showLoadingDialog();
        feedService.getFeeds();
    }

    private void stopLoadings() {
        swipeRefreshLayout.setRefreshing(false);
        alertUtil.dissmissDialog();
    }

}
