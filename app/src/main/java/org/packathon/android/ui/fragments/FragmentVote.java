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
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.packathon.android.R;
import org.packathon.android.enums.FragmentType;
import org.packathon.android.events.MeEvent;
import org.packathon.android.events.ProjectsEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.models.ProjectListModel;
import org.packathon.android.services.ProjectService;
import org.packathon.android.services.UserService;
import org.packathon.android.ui.adapters.ProjectAdapter;
import org.packathon.android.ui.events.SwitchFragment;
import org.packathon.android.utils.AlertUtil;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_vote)
public class FragmentVote extends BaseFragment {

    @Bean AlertUtil alertUtil;
    @Bean UserService userService;
    @Bean ProjectService projectService;

    @ViewById(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.project_list)
    protected ListView projectList;

    @ViewById(R.id.error_container)
    protected LinearLayout errorContainer;

    private List<ProjectListModel> projects;
    private int votedProject;

    @AfterViews
    public void afterViews() {
        setFragmentType(FragmentType.VOTE);
        setTitle(getString(R.string.title_vote));

        alertUtil.showLoadingDialog();
        userService.whoAmI();
    }

    @Subscribe
    public void iKnowMyself(MeEvent result) {
        if (result.hasError()) {
            projectList.setVisibility(View.GONE);
            errorContainer.setVisibility(View.VISIBLE);
            stopLoadings();
            return;
        }

        votedProject = result.meModel.voted_project;
        projectService.getProjects();
    }

    @Subscribe
    public void onProjectsGet(ProjectsEvent result) {
        if (result.hasError()) {
            projectList.setVisibility(View.GONE);
            errorContainer.setVisibility(View.VISIBLE);
            stopLoadings();
            return;
        }

        projectList.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);

        projects = result.projectModel.results;

        ProjectAdapter adapter = new ProjectAdapter(
                getContext(),
                R.layout.item_project,
                (ArrayList<ProjectListModel>) projects,
                votedProject
        );
        projectList.setAdapter(adapter);

        getSwipeRefreshLayout();

        stopLoadings();
    }

    private void getSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userService.whoAmI();
            }
        });
    }

    @ItemClick(R.id.project_list)
    public void onProjectListItemClick(int position) {
        BusHelper.BUS.post(new SwitchFragment(getActivity().getSupportFragmentManager(), FragmentProjectDetail_.newInstance(projects.get(position).id), true));
    }

    @Click(R.id.try_again)
    public void onTryAgainClicked() {
        alertUtil.showLoadingDialog();
        userService.whoAmI();
    }

    private void stopLoadings() {
        swipeRefreshLayout.setRefreshing(false);
        alertUtil.dissmissDialog();
    }

}
