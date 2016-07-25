package com.ciandt.dojoandroid.marvelsapp.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vnaraujo on 08/07/2016.
 */
public class Thumbnail implements Parcelable {

    private int flattenData;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("extension")
    @Expose
    private String extension;

    @Override
    public int describeContents() {
        return 2;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(flattenData);
    }

    public static final Parcelable.Creator<Thumbnail> CREATOR = new Parcelable.Creator<Thumbnail>() {
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    private Thumbnail(Parcel in) {
        flattenData = in.readInt();
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }
}
