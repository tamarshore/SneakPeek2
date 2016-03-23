package com.studio4us.tamar.sneakpeek2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.parse.ParseFile;
import com.parse.ParseObject;

// In this case, the fragment displays simple text based on the page
public class User extends Fragment implements View.OnClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    Button save;
    //company
    EditText company;
    String scompany;

    //position
    EditText position;
    String sposition;

    //intreview
    EditText calendar;
    String sinterview;


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
        company = (EditText) view.findViewById(R.id.company);
        position = (EditText) view.findViewById(R.id.position);
        calendar = (EditText) view.findViewById(R.id.calendar);
        save = (Button) view.findViewById(R.id.button);
        save.setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        //Toast for empty tip
        CharSequence text = "Please insert content";
        CharSequence uploadingText = "searching...";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), text, duration);
        Toast uploadingToast = Toast.makeText(getContext(), uploadingText, duration);

        scompany = company.getText().toString();
        sposition = position.getText().toString();
        sinterview = calendar.getText().toString();

        if (!scompany.matches("") || !sposition.matches("")) {
            uploadingToast.show();

            ParseObject tipObject = new ParseObject("Profile");
            tipObject.put("Company", scompany);
            tipObject.put("Position", sposition);
            tipObject.put("InterviewDate", sinterview);
            tipObject.saveInBackground();
        } else {
            toast.show();
        }

        }
}

