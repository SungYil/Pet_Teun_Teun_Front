package com.pethospital.pet_teun_teun.items;

import android.graphics.drawable.Drawable;

public class VaccineViewItem {
    private Drawable icon;
    private String date;
    private String name;
    private String subName;
    private String won;

    public VaccineViewItem(){
        this.icon=null;
        this.date="emp";
        this.name="emp";
        this.subName="emp";
        this.won="emp";
    }
    public VaccineViewItem(Drawable icon, String date, String name, String subName, String won) {
        this.icon = icon;
        this.date = date;
        this.name = name;
        this.subName = subName;
        this.won = won;
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

    public String getWon() {
        return won;
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

    public void setWon(String won) {
        this.won = won;
    }
}
