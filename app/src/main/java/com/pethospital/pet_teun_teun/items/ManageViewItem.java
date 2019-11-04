package com.pethospital.pet_teun_teun.items;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.pethospital.pet_teun_teun.R;

public class ManageViewItem implements Parcelable {
    private String id;
    private Drawable icon;
    private String name;
    private String subname;
    private String content;

    public ManageViewItem(){
        this.icon=null;
        this.name="emp";
        this.subname="emp";
        this.content="emp";
    }
    public ManageViewItem(Parcel in){
        this.id=in.readString();
        this.name=in.readString();
        this.subname=in.readString();
        this.content=in.readString();
    }
    public ManageViewItem(String id,Drawable icon, String name, String subname, String content) {
        this.id=id;
        this.icon = icon;
        this.name = name;
        this.subname = subname;
        this.content = content;
    }
    public String getId(){return id;}
    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getSubname() {
        return subname;
    }

    public String getContent() {
        return content;
    }
    public void setId(String id){this.id=id;}

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final Creator<ManageViewItem> CREATOR = new Creator<ManageViewItem>() {
        @Override
        public ManageViewItem createFromParcel(Parcel in) {
            return new ManageViewItem(in);
        }

        @Override
        public ManageViewItem[] newArray(int size) {
            return new ManageViewItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(subname);
        dest.writeString(content);
    }
}
