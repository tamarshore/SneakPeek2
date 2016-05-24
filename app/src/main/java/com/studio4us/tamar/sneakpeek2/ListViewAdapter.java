package com.studio4us.tamar.sneakpeek2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
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
//    boolean isLiked = false;
    boolean[] isLiked;

    public ListViewAdapter(Context context,
                           List<TipsContent> tips) {
        mContext = context;
        this.tips = tips;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(tips);

        isLiked = new boolean[tips.size()];
        for(int i = 0; i < tips.size(); i++){
          isLiked[i] = false;
        }

    }

    public class ViewHolder {
        //tip content
        TextView t;
        TextView likes;
        TextView tags;
        ImageView imageView;
        ImageButton likesCounter;

        //user details
//        TextView userName;
//        ImageView userImage;
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

    public void setButtonAsLike(ImageView button, String id){
        button.setBackgroundResource(R.drawable.thumb_up_outline);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tip");
        if (id != null){
            query.getInBackground(id, new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.increment("Likes", -1);
                                object.saveInBackground();
                            } else {
                                // something went wrong
                            }
                        }
            });
        }
        notifyDataSetChanged();
    }

    public void setButtonAsUnlike(ImageView button, String id){
        button.setBackgroundResource(R.drawable.thumb_up);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tip");
        if (id != null){
            query.getInBackground(id, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        object.increment("Likes");
                        object.saveInBackground();
                    } else {
                        // something went wrong
                    }
                }
            });
        }
        notifyDataSetChanged();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Locate the TextViews in listview_item.xml
        holder.t = (TextView) view.findViewById(R.id.tip);
        holder.likes = (TextView) view.findViewById(R.id.likes_counter);
        holder.tags = (TextView) view.findViewById(R.id.hashtag);
        holder.imageView = (ImageView) view.findViewById(R.id.img);
        holder.likesCounter = (ImageButton) view.findViewById(R.id.like_icon);
        holder.likesCounter.setTag(position);

        if (isLiked[position])
            setButtonAsUnlike(holder.likesCounter, null);

        else
            setButtonAsLike(holder.likesCounter, null);

        holder.likesCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = tips.get(position).getObjectId();

                if (!isLiked[position]) {
                    // Not liked before, so like now.
                    isLiked[position] = true; // store this value.
                    setButtonAsUnlike(holder.likesCounter, id); // the button is now "unlike"
                    tips.get(position).increaseLikes();

                } else {
                    // Liked before, unliked now.
                    isLiked[position] = false; // store this value.
                    setButtonAsLike(holder.likesCounter, id); // the button is now "like"
                    tips.get(position).decreaseLikes();
                }
            }
        });
        view.setTag(holder);

//        Bitmap userImage = tips.get(position).getUserImage();
//        if(userImage != null) {
//            holder.userImage.setImageBitmap(userImage);
//            android.view.ViewGroup.LayoutParams layoutParams = holder.userImage.getLayoutParams();
//            layoutParams.width = 1250;
//            layoutParams.height = 1250;
//            holder.userImage.setLayoutParams(layoutParams);
//        }
//        holder.userName.setText(tips.get(position).getUserName());
        Bitmap image = tips.get(position).getImage();
        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(image != null){
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageBitmap(image);
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            android.view.ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;

            holder.imageView.setLayoutParams(layoutParams);
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
        // Set the results into TextViews
        holder.likes.setText(tips.get(position).getLikes());
        holder.t.setText(tips.get(position).getTip());
        holder.tags.setText(tips.get(position).getTags());
        return view;
    }


}