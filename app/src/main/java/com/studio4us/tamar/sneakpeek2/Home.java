package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// In this case, the fragment displays simple text based on the page
public class Home extends Fragment {
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<TipsContent> tips = null;
    ArrayList<TipsContent> mAllData = new ArrayList<TipsContent>();
    String searchString;
    Context con;
    View view;
    EditText searchFiled;
    boolean isVisable = false;
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
        setHasOptionsMenu(true);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.home_page, container, false);
        con = getActivity();
        searchFiled = (EditText) view.findViewById(R.id.searchFiled);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
        doSearch();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(!isVisable) {
                    searchFiled.setVisibility(View.VISIBLE);
                }else{
                    searchFiled.setVisibility(View.GONE);
                }
                isVisable = !isVisable;
                return true;
            }
        });
    }

    private void doSearch() {
        final EditText et = (EditText)view.findViewById(R.id.searchFiled);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = et.getText().toString().toLowerCase(Locale.getDefault());
                filter(text);
            }
        });
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        tips.clear();
        if (charText.length() == 0) {
            tips.addAll(mAllData);
        } else {
            for (TipsContent wp : mAllData) {
                if (wp.getTags().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    tips.add(wp);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void updateTips(){
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
        } catch (com.parse.ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        mAllData.addAll(tips);
    }


    public class RemoteDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(con);
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            updateTips();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) getView().findViewById(R.id.list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(con, tips);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();

        }
    }
}

