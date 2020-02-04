package com.evs.android.mysampleapp.week11.viewpager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class SliderItem {

    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public SliderItem(String caption, String imageUrl) {
        setCaption(caption);
        setImageUrl(imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
