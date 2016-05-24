package com.studio4us.tamar.sneakpeek2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    MenuItem search;
    ViewPager viewPager;
    SharedPreferences mPrefs;
//    final String welcomeScreenShownPref = "welcomeScreenShown";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set the welcome screen only one time
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // second argument is the default to use if the preference can't be found
//        Boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);
//        Intent loginIntent = new Intent(this, UserDetails.class);
//        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(loginIntent);
//        finish();
//        if (!welcomeScreenShown) {
            //show the welcome screen in order to register
//            Intent intent = new Intent(this, Welcome.class);
//            startActivity(intent);
//            SharedPreferences.Editor editor = mPrefs.edit();
//            editor.putBoolean(welcomeScreenShownPref, false);
//            editor.commit(); // Very important to save the preference
//        }


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setBackgroundColor(Color.BLACK);

        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        search = menu.findItem(R.id.action_search);
        search.setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }


    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
        final int PAGE_COUNT = 2;
        private int tabIcons[] = {R.drawable.ic_home, R.drawable.upload};

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - Home
                    return Home.newInstance();
                case 1: // Fragment # 1 - Upload
                    return Upload.newInstance();
//                case 2: // Fragment # 2 - User
//                    return User.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getPageIconResId(int position) {
            // Generate title based on item position
            return tabIcons[position];
        }

    }
}
