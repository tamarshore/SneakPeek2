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

// In this case, the fragment displays simple text based on the page
public class Upload extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    //database
//    FeedReaderDbHelper feedDB;
    private int mPage;

    //details
//    String company;
//    String content;
//    String tags;
//
//    Button mButton;
//    EditText editCompany;
//    EditText editContent;
//    EditText editTags;

    public static Upload newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Upload upload = new Upload();
        upload.setArguments(args);
        return upload;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);



    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ulpoad_page, container, false);

//        mButton = (Button) view.findViewById(R.id.button);
//        editCompany = (EditText) view.findViewById(R.id.company);
//        editContent = (EditText) view.findViewById(R.id.content);
//        editTags = (EditText) view.findViewById(R.id.tags);
//
//        mButton.setOnClickListener(
//                new View.OnClickListener()
//                {
//                    public void onClick(View view)
//                    {
//                        company = editCompany.getText().toString();
//                        content = editCompany.getText().toString();
//                        tags = editCompany.getText().toString();
//                    }
//                });
//        feedDB = new FeedReaderDbHelper(getContext());
//        // Gets the data repository in write mode
//        SQLiteDatabase db = feedDB.getWritableDatabase();
//
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_FEED_ID, company);
//        values.put(FeedReaderContract.FeedEntry.COLUMN_CONTENT, content);
//        values.put(FeedReaderContract.FeedEntry.COLUMN_TAGS, tags);
//
//        // Insert the new row, returning the primary key value of the new row
//        long newRowId;
//        newRowId = db.insert(
//                FeedReaderContract.FeedEntry.TABLE_NAME,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_NULLABLE,
//                values);
        return view;
    }

}