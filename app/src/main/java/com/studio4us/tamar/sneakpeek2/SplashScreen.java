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
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
//            R.drawable.wix,
//            R.drawable.google,
//            R.drawable.waze,
//            R.drawable.intel,
//            R.drawable.similarweb,
//            R.drawable.dropbox,
//            R.drawable.whats,
//            R.drawable.insta,
//            R.drawable.similarweb,
//            R.drawable.dropbox,
//            R.drawable.whats,
//            R.drawable.insta,
//            R.drawable.whats,
//            R.drawable.insta,


//            R.drawable.viber,
//            R.drawable.linkedin,

//            R.drawable.microsoft,
//            R.drawable.vernit,

//            R.drawable.ibm,
//            R.drawable.ebay,




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
