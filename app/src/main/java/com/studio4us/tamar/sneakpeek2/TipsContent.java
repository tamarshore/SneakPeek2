package com.studio4us.tamar.sneakpeek2;

import android.graphics.Bitmap;

import com.parse.ParseFile;

import java.io.File;

/**
 * Created by Tamar on 3/15/16.
 */
public class TipsContent {
    private String tip;
    private int likes;
    private String tag;
//    private String userName;
    private Bitmap image;
//    private Bitmap userImage;
    private String obId;


    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTags(){
        return tag;
    }

    public void setTags(String tag){
        this.tag = tag;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public String getLikes() {
        return String.valueOf(likes);
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setObjectId(String obId) {
        this.obId = obId;
    }

    public String getObjectId() {
        return this.obId;
    }

    public void increaseLikes(){
        this.likes++;
    }
    public void decreaseLikes(){
        this.likes--;
    }
//    public Bitmap getUserImage() {
//        return this.userImage;
//    }
//
//    public void setUserImage(Bitmap image) {
//        this.userImage = image;
//    }

//    public String getUserName(){
//        return this.userName;
//    }
//
//    public void setUserName(String userName){
//        this.userName = userName;
//    }
}
