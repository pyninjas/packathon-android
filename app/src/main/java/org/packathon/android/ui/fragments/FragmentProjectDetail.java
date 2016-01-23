package org.packathon.android.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.packathon.android.R;
import org.packathon.android.enums.FragmentType;
import org.packathon.android.events.MeEvent;
import org.packathon.android.events.ProjectEvent;
import org.packathon.android.events.TeamEvent;
import org.packathon.android.events.VoteEvent;
import org.packathon.android.models.ProjectListModel;
import org.packathon.android.models.TeamListModel;
import org.packathon.android.models.UserListModel;
import org.packathon.android.services.ProjectService;
import org.packathon.android.services.TeamService;
import org.packathon.android.services.UserService;
import org.packathon.android.services.VoteService;
import org.packathon.android.utils.AlertUtil;

@EFragment(R.layout.fragment_project_detail)
public class FragmentProjectDetail extends BaseFragment {

    @Bean AlertUtil alertUtil;
    @Bean ProjectService projectService;
    @Bean VoteService voteService;
    @Bean UserService userService;
    @Bean TeamService teamService;

    private int projectId;

    @ViewById(R.id.content_container)
    protected RelativeLayout contentContainer;

    @ViewById(R.id.error_container)
    protected LinearLayout errorContainer;

    @ViewById(R.id.name)
    protected TextView nameView;

    @ViewById(R.id.team)
    protected TextView teamView;

    @ViewById(R.id.team_icon)
    protected ImageView teamIcon;

    @ViewById(R.id.members)
    protected TextView membersView;

    @ViewById(R.id.button_vote)
    protected TextView buttonVote;

    private ProjectListModel project;
    private int votedProject;
    private TeamListModel team;

    @AfterViews
    public void afterViews() {
        setFragmentType(FragmentType.PROJECT_DETAIL);
        setTitle(getString(R.string.title_project_detail));

        Bundle bundle = getArguments();
        if (bundle != null) {
            projectId = bundle.getInt("PROJECT_ID");
        }

        alertUtil.showLoadingDialog();
        userService.whoAmI();
    }

    @Subscribe
    public void iKnowMyself(MeEvent result) {
        if (result.hasError()) {
            contentContainer.setVisibility(View.GONE);
            errorContainer.setVisibility(View.VISIBLE);
            alertUtil.dissmissDialog();
            return;
        }

        votedProject = result.meModel.voted_project;
        projectService.getProject(projectId);
    }

    @Subscribe
    public void onGetProject(ProjectEvent result) {
        if (result.hasError()) {
            contentContainer.setVisibility(View.GONE);
            errorContainer.setVisibility(View.VISIBLE);
            alertUtil.dissmissDialog();
            return;
        }

        project = result.projectListModel;
        teamService.getTeam(project.team.id);
    }

    @Subscribe
    public void onGetTeam(TeamEvent result) {
        if (result.hasError()) {
            contentContainer.setVisibility(View.GONE);
            errorContainer.setVisibility(View.VISIBLE);
            alertUtil.dissmissDialog();
            return;
        }

        contentContainer.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);

        team = result.teamListModel;

        setTitle(project.name);

        if (votedProject == projectId) {
            setVoteButtonEnabled(true);
        } else {
            setVoteButtonEnabled(false);
        }

        nameView.setText(project.name);
        teamView.setText(project.team.name);

        if (team != null && team.users != null && team.users.size() > 0) {
            String members = "";
            for (UserListModel member : team.users) {
                members += member.name + "\n";
            }
            membersView.setText(members);
        } else {
            teamIcon.setVisibility(View.GONE);
            membersView.setVisibility(View.GONE);
        }

        alertUtil.dissmissDialog();
    }

    private void setVoteButtonEnabled(boolean isVoted) {
        if (isVoted) {
            buttonVote.setEnabled(false);
            buttonVote.setAlpha((float) 0.75);
            buttonVote.setText(getString(R.string.label_project_voted));
        } else {
            buttonVote.setEnabled(true);
            buttonVote.setAlpha((float) 1);
            buttonVote.setText(getString(R.string.label_vote_this_project));
        }
    }

    @Click(R.id.try_again)
    public void onTryAgainClicked() {
        alertUtil.showLoadingDialog();
        projectService.getProject(projectId);
    }

    @Click(R.id.button_source_code)
    public void onSourceCodeClicked() {
        if (project.git != null && !project.git.equals("")) {
            openUrl(project.git);
        } else {
            alertUtil.showMessage(getString(R.string.err_source_code_not_exist));
        }
    }

    @Click(R.id.button_website)
    public void onWebSiteClicked() {
        if (project.website != null && !project.website.equals("")) {
            openUrl(project.website);
        } else {
            alertUtil.showMessage(getString(R.string.err_website_not_exist));
        }
    }

    @Click(R.id.button_vote)
    public void onVoteClicked() {
        alertUtil.showLoadingDialog();
        voteService.vote(projectId);
    }

    @Subscribe
    public void onVote(VoteEvent result) {
        if (result.hasError()) {
            alertUtil.dissmissDialog();
            alertUtil.showMessage(getString(R.string.error_title), result.getError().message);
            return;
        }

        voteService.saveVote(projectId);
        setVoteButtonEnabled(true);

        alertUtil.dissmissDialog();
        alertUtil.showMessage(getString(R.string.msg_voting));
    }

    private void openUrl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static FragmentProjectDetail newInstance(int projectId) {
        FragmentProjectDetail_ fragment = new FragmentProjectDetail_();
        Bundle bundle = new Bundle();
        bundle.putInt("PROJECT_ID", projectId);
        fragment.setArguments(bundle);
        return fragment;
    }

}
