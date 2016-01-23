package org.packathon.android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import org.packathon.android.R;
import org.packathon.android.helpers.DateHelper;
import org.packathon.android.models.FeedListModel;

import java.util.ArrayList;

public class FeedAdapter extends ArrayAdapter<FeedListModel> {

    private static final String TAG = "FeedAdapter";

    private Context context;
    private int resource;
    private ArrayList<FeedListModel> items;

    public FeedAdapter(Context context, int resource, ArrayList<FeedListModel> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
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

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView description = (TextView) view.findViewById(R.id.description);
        RelativeTimeTextView date = (RelativeTimeTextView) view.findViewById(R.id.date);

        title.setText(items.get(position).title);
        description.setText(items.get(position).description);
        date.setReferenceTime(DateHelper.getDate(items.get(position).created_at).getTime());

        return view;
    }

}
