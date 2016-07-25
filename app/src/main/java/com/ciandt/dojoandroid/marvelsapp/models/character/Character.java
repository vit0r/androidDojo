package com.ciandt.dojoandroid.marvelsapp.models.character;

import android.os.Parcel;
import android.os.Parcelable;

import com.ciandt.dojoandroid.marvelsapp.models.comic.Comic;
import com.ciandt.dojoandroid.marvelsapp.utils.models.Thumbnail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vitor on 07/07/2016.
 * https://developer.android.com/reference/android/os/Parcelable.html#PARCELABLE_WRITE_RETURN_VALUE
 */
public class Character implements Parcelable {
    private int flattenData;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("comics")
    @Expose
    private Comic comics;

    public int describeContents() {
        return 1;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(flattenData);
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    private Character(Parcel in) {
        flattenData = in.readInt();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comic getComics() {
        return comics;
    }

    public void setComics(Comic comics) {
        this.comics = comics;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
