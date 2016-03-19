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

import com.parse.GetCallback;
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
    boolean isLiked = false;

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
        ImageButton likesCounter;
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
//            holder.imageView = (ImageView) view.findViewById(R.id.img);
            holder.likesCounter = (ImageButton) view.findViewById(R.id.like_icon);
            holder.likesCounter.setBackgroundResource(R.drawable.ic_favorite_border_black_18dp);
            holder.likesCounter.setTag(position);
            holder.likesCounter.setOnClickListener(new android.view.View.OnClickListener() {
                public void onClick(View v) {
                    isLiked = !isLiked; // reverse
                    final int position = (Integer) v.getTag();
                    String id = tips.get(position).getObjectId();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Tip");

                    //If the likes icon is clicked
                    if(isLiked) {
                        v.setBackgroundResource(R.drawable.ic_favorite_black_18dp);
                        query.getInBackground(id, new GetCallback<ParseObject>() {
                            public void done(ParseObject object, ParseException e) {
                                if (e == null) {
                                    object.increment("Likes");
                                    object.saveInBackground();
                                    tips.get(position).setLikes(object.getInt("Likes"));
                                    notifyDataSetChanged();
                                } else {
                                    // something went wrong
                                }
                            }
                        });
                        //If the likes icon is unclicked
                    } else {
                    v.setBackgroundResource(R.drawable.ic_favorite_border_black_18dp);
                    query.getInBackground(id, new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.increment("Likes", -1);
                                object.saveInBackground();
                                tips.get(position).setLikes(object.getInt("Likes"));
                                notifyDataSetChanged();
                            } else {
                                // something went wrong
                            }
                        }
                    });
                }


                }
            });

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.likes.setText(tips.get(position).getLikes());
        holder.t.setText(tips.get(position).getTip());
        holder.tags.setText(tips.get(position).getTags());

        return view;
    }


}