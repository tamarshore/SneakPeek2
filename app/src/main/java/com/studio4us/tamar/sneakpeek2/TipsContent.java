package com.studio4us.tamar.sneakpeek2;

/**
 * Created by Tamar on 3/15/16.
 */
public class TipsContent {
    private String tip;
    private int likes;

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

    public void addToLikes() {
        this.likes += 1;
    }
}
