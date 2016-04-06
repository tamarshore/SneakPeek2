package com.studio4us.tamar.sneakpeek2;

import android.graphics.Bitmap;

/**
 * Created by Tamar on 4/6/16.
 */
public class UserDetails {
    private String userName;
    private Bitmap userImage;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bitmap getUserImage() {
        return this.userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }
}
