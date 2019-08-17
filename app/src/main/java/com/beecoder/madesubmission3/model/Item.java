package com.beecoder.madesubmission3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    String photo;
    String title;
    String description;

    public Item(String title, String photo, String description){
        this.title = title;
        this.photo = photo;
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.photo = in.readString();
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
