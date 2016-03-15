package com.studio4us.tamar.sneakpeek2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<TipsContent> tips = null;
    private ArrayList<TipsContent> arraylist;

    public ListViewAdapter(Context context,
                           List<TipsContent> tips) {
        mContext = context;
        this.tips = tips;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(tips);
    }

    public class ViewHolder {
        TextView t;
    }

    @Override
    public int getCount() {
        return tips.size();
    }

    @Override
    public TipsContent getItem(int position) {
        return tips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.t = (TextView) view.findViewById(R.id.tip);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.t.setText(tips.get(position).getTip());



        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("rank",
                        (tips.get(position).getTip()));
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}