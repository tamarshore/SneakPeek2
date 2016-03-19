package com.studio4us.tamar.sneakpeek2;

import java.io.File;

/**
 * Created by Tamar on 3/15/16.
 */
public class TipsContent {
    private String tip;
    private int likes;
    private String tag;
    private File image;
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

    public File getImage(){
        return image;
    }

    public void setImage(File image){
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

}
