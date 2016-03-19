package com.studio4us.tamar.sneakpeek2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<TipsContent> tips = null;
    private ArrayList<TipsContent> arraylist;
    private ViewHolder holder;

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
        TextView likes;
        TextView tags;
        ImageView imageView;
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
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.t = (TextView) view.findViewById(R.id.tip);
            holder.likes = (TextView) view.findViewById(R.id.likes_counter);
            holder.tags = (TextView) view.findViewById(R.id.hashtag);
            holder.imageView = (ImageView) view.findViewById(R.id.img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Set the results into TextViews
        holder.likes.setText(tips.get(position).getLikes());
        holder.t.setText(tips.get(position).getTip());
        holder.tags.setText(tips.get(position).getTags());




        // Listen for ListView Item Click
//        view.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(mContext, SingleItemView.class);
//                // Pass all data tip
//                intent.putExtra("tip", (tips.get(position).getTip()));
//                intent.putExtra("likes", (tips.get(position).getLikes()));
//                // Start SingleItemView Class
//                mContext.startActivity(intent);
//            }
//        });

        return view;
    }

//    public void myClickHandler() throws ParseException {
//        try {
//            // Locate the class table named "Country"
//            ParseQuery<ParseObject> query = new ParseQuery<>("Tip");
//            // Locate the column named "TipContent" i
//            // by ascending
//            query.orderByAscending("createdAt");
//            ob = query.find();
//            ParseObject tipObject = new ParseObject("Tip");
//            tipObject.put("Likes", +1);
//            tipObject.saveInBackground();
//
//            ParseQuery<ParseObject> query = new ParseQuery<>("Tip");
//            List<ParseObject> ob;
//            query.orderByAscending("createdAt");
//            ob = query.find();
//
//
//        }
//    }

}