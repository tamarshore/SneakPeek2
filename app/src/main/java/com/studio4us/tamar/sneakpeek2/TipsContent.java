package com.studio4us.tamar.sneakpeek2;

/**
 * Created by Tamar on 3/15/16.
 */
public class TipsContent {
    private String tip;
    private int likes;
    private String obId;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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
