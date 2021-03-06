package com.assignment.satya.cartrack.ui.view;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.satya.cartrack.R;
import com.assignment.satya.cartrack.room.model.RestUserModel;

import java.util.List;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder> {
    private List<RestUserModel> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.userName);
            txtFooter = (TextView) v.findViewById(R.id.email);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UsersRecyclerViewAdapter(List<RestUserModel> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UsersRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_users, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final RestUserModel row = values.get(position);
        holder.txtHeader.setText(row.getName());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = row.getAddress().getStreet();
                String uriBegin = "geo:" + row.getAddress().getGeo().getLat() + "," + row.getAddress().getGeo().getLng();
                String query = row.getAddress().getGeo().getLat() + "," + row.getAddress().getGeo().getLng() + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery;
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                mapIntent.setPackage("com.google.android.apps.maps");
                holder.txtHeader.getContext().startActivity(mapIntent);
            }
        });
        holder.txtFooter.setText(row.getEmail());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
