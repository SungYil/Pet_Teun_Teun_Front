package com.pethospital.pet_teun_teun.items;

import android.graphics.drawable.Drawable;

public class PetStateViewItem {
    private Drawable icon;
    private String date;
    private String name;
    private String subName;

    public PetStateViewItem(){
        this.icon=null;
        this.date="emp";
        this.name="emp";
        this.subName="emp";
    }
    public PetStateViewItem(Drawable icon, String date, String name, String subName) {
        this.icon = icon;
        this.date = date;
        this.name = name;
        this.subName = subName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getSubName() {
        return subName;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
