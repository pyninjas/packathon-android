package org.packathon.android.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.packathon.android.R;
import org.packathon.android.models.ProjectListModel;
import org.packathon.android.models.ProjectTeamModel;

import java.util.ArrayList;

public class ProjectAdapter extends ArrayAdapter<ProjectListModel> {

    private static final String TAG = "ProjectAdapter";

    private Context context;
    private int resource;
    private ArrayList<ProjectListModel> items;
    private int votedProject;

    public ProjectAdapter(Context context, int resource, ArrayList<ProjectListModel> items, int votedProject) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
        this.votedProject = votedProject;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        }
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        RelativeLayout projectLayout = (RelativeLayout) view.findViewById(R.id.project_layout);
        TextView titleView = (TextView) view.findViewById(R.id.name);
        TextView teamView = (TextView) view.findViewById(R.id.team);
        ImageView votedIcon = (ImageView) view.findViewById(R.id.voted_icon);

        if (votedProject == items.get(position).id) {
            projectLayout.setBackgroundColor(Color.parseColor("#DFF0D8"));
            votedIcon.setVisibility(View.VISIBLE);
        }

        titleView.setText(items.get(position).name);

        ProjectTeamModel team = items.get(position).team;
        if (team != null)
            teamView.setText(team.name);

        return view;
    }

}
