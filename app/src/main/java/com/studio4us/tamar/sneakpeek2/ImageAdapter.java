package com.studio4us.tamar.sneakpeek2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by shanibilu on 3/15/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    int[] images;

    public ImageAdapter(Context c, int[] images) {
        this.mContext = c;
        this.images = images;
    }

    public int getCount() {
        return images.length;
    }

    public Object getItem(int position) {
        return images[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {


        ImageView imageView = new ImageView(mContext);
        Picasso.with(mContext).load(images[position]).into(imageView);
//        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageView.setLayoutParams(new GridView.LayoutParams(360, 360));


        return imageView;
    }

}
