package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

/**
 * Created by shanibilu on 3/15/16.
 */
public class SplashScreen extends Activity {

    GridView gridview;

    // references to our images
    int[] images = {
            R.drawable.airbnb,
            R.drawable.amazon,
            R.drawable.ebay,
            R.drawable.etsy,
            R.drawable.google,
            R.drawable.marmelada_market,
            R.drawable.netflix_logo,
            R.drawable.similarweb,
            R.drawable.snapchat,
            R.drawable.viber,
            R.drawable.twitter,
            R.drawable.waze,
            R.drawable.whatsapp,
            R.drawable.linkedin,
            R.drawable.moovit,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        gridview = (GridView) findViewById(R.id.gridView);
        ImageAdapter adapter = new ImageAdapter(this, images);
        gridview.setAdapter(adapter);

        Thread thread = new Thread(){
            public void run(){
                try{

                    sleep(6200);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };
        thread.start();
    }
}
