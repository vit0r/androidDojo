package com.ciandt.dojoandroid.marvelsapp.models.comic;

import android.os.Parcel;
import android.os.Parcelable;

import com.ciandt.dojoandroid.marvelsapp.utils.models.Thumbnail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by vnaraujo on 20/07/2016.
 */
public class Comic implements Parcelable{
    private int flattenData;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("modified")
    @Expose
    private Date modified;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Date getModified() {
        return modified;
    }

    public String getTitle() {
        return title;
    }

    public int describeContents() {
        return 3;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(flattenData);
    }

    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };

    private Comic(Parcel in) {
        flattenData = in.readInt();
    }

    public Integer getReturned() {
        return returned;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }
}
