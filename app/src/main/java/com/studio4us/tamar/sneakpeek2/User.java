package com.studio4us.tamar.sneakpeek2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

// In this case, the fragment displays simple text based on the page
public class User extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static User newInstance() {
        Bundle args = new Bundle();
        User user = new User();
        user.setArguments(args);
        return user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_page, container, false);

        return view;
    }
}

