package com.studio4us.tamar.sneakpeek2;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;


public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "H5AVFyTBNwFX5DD7R1mTE2Syx0MVPJhAZhk4yxgz", "doycEUOtqY5sOey3FIDUdOGffNBUMGVpM3H47KSA");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();


    }

}