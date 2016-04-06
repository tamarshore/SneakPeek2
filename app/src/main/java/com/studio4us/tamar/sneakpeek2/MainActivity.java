package com.studio4us.tamar.sneakpeek2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity{
    android.support.v7.widget.SearchView searchView = null;
    ViewPager viewPager;
    SharedPreferences mPrefs;
    final String welcomeScreenShownPref = "welcomeScreenShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set the wlecom screen only one time
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // second argument is the default to use if the preference can't be found
//        Boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);
//        Intent loginIntent = new Intent(MainActivity.this, UserDetails.class);
//        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(loginIntent);
//        finish();
//        if (!welcomeScreenShown) {
//            //show the welcome screen in order to register
//            Intent intent = new Intent(this, Welcome.class);
//            startActivity(intent);
//            SharedPreferences.Editor editor = mPrefs.edit();
////            editor.putBoolean(welcomeScreenShownPref, true);
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
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

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
