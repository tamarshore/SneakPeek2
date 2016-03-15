package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItemView extends Activity {
    // Declare Variables
    TextView tipContent;

    String tip;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        tip = i.getStringExtra("tip");


        // Locate the TextViews in singleitemview.xml
        tipContent = (TextView) findViewById(R.id.tip);


        // Load the results into the TextViews
        tipContent.setText(tip);

    }
}
