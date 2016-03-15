package com.studio4us.tamar.sneakpeek2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;

// In this case, the fragment displays simple text based on the page
public class Upload extends Fragment implements View.OnClickListener {

    EditText content;
    String tip;
    Button upload;

    public static Upload newInstance() {
        Bundle args = new Bundle();
        Upload upload = new Upload();
        upload.setArguments(args);
        return upload;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ulpoad_page, container, false);
        content = (EditText) view.findViewById(R.id.content);
        tip = content.getText().toString();
        upload = (Button) view.findViewById(R.id.button);
        upload.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ParseObject testObject = new ParseObject("Tip");
        testObject.put("TipContent", tip);
        testObject.saveInBackground();
    }
}