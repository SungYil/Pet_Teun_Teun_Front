package com.pethospital.pet_teun_teun.items;

import android.os.Parcel;
import android.os.Parcelable;

public class ReserveCheckItem implements Parcelable {
    private String id;
    private String type;
    private String time;
    private String careType;
    private String name;

    public ReserveCheckItem() {
    }

    public ReserveCheckItem(ReserveCheckItem item){
        this(item.getId(),item.getType(),item.getTime(),item.getCareType(),item.getName());
    }
    public ReserveCheckItem(Parcel in){
        this.name=in.readString();
        this.type=in.readString();
        this.time=in.readString();
        this.careType =in.readString();
        this.name=in.readString();
    }
    public ReserveCheckItem(String id, String type, String time, String careType,String name) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.careType = careType;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCareType() {
        return careType;
    }

    public void setCareType(String careType) {
        this.careType = careType;
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
        dest.writeString(type);
        dest.writeString(time);
        dest.writeString(careType);
        dest.writeString(name);
    }
}
