package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// In this case, the fragment displays simple text based on the page
public class Home extends Fragment implements ITextSubmit {
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<TipsContent> tips = new ArrayList<>();
    String searchString;
    Context con;
    private View rootView;
//    ImageButton likes;


    public static Home newInstance() {
        Bundle args = new Bundle();
        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.home_page, container, false);
        con = getActivity();

        // Locate the listview in listview_main.xml
        listview = (ListView) view.findViewById(R.id.list);
        // Pass the results into ListViewAdapter.java
        adapter = new ListViewAdapter(con);
        // Binds the Adapter to the ListView
        listview.setAdapter(adapter);

        adapter.setDataList(tips);

        return view;
    }

    private void filterContentTipsByTag(String tag) {

        if(tips == null) {
            Log.d("yarkoni", "tips list is null");
            return;
        }

        if(TextUtils.isEmpty(tag)) {
            Log.d("yarkoni", "search tag is empty");
            return;
        }

        List<TipsContent> filteredTipsContentList = new ArrayList<>();

        for(TipsContent tipsContent:tips) {

            if(tag.equals(tipsContent.getTags())) {

                filteredTipsContentList.add(tipsContent);
            }
        }

        adapter.setDataList(filteredTipsContentList);

    }

    @Override
    public void onSubmitText(String str) {

        new RemoteDataTask().execute(str);
    }

    public class RemoteDataTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
//            mProgressDialog = new ProgressDialog(con);
//            // Set progressdialog message
//            mProgressDialog.setMessage("Loading...");
//            mProgressDialog.setIndeterminate(false);
//            // Show progressdialog
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            // Create the array
            tips = new ArrayList<>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<>("Tip");
                // Locate the column named "TipContent" in Parse.com and order list
                // by ascending
                query.orderByAscending("createdAt");
                ob = query.find();
                for (ParseObject t : ob) {
                    TipsContent map = new TipsContent();
                    map.setTip((String) t.get("TipContent"));
                    map.setLikes((int) t.get("Likes"));
                    map.setTags((String) t.get("Tags"));
//                    map.setImage((File) t.get("ImageFile"));
                    map.setObjectId(t.getObjectId());
                    tips.add(map);
                }

                if(params.length != 0) {

                    String searchString = params[0];
                    if(!TextUtils.isEmpty(searchString)) {
                        filterContentTipsByTag(searchString);
                    } else {
                        Log.d("Yarkoni", "searchString is null");
                    }
                }

            } catch (com.parse.ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Close the progressdialog
//            mProgressDialog.dismiss();

        }
    }
}

