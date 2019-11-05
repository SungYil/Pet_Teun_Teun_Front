package com.pethospital.pet_teun_teun.items;

import android.graphics.drawable.Drawable;

public class CommitViewItem {
    private String id;
    private Drawable icon;
    private String date;
    private String name;
    private String subName;
    private String contents;

    public CommitViewItem(){
        this.icon=null;
        this.date="19/19/19";
        this.name="emp";
        this.subName="emp";
        this.contents="emp";
    }
    public CommitViewItem(String id,Drawable icon, String date, String name, String subName, String contents) {
        this.id=id;
        this.icon = icon;
        this.date = date;
        this.name = name;
        this.subName = subName;
        this.contents = contents;
    }
    public String getId(){return id;}
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

    public String getContents() {
        return contents;
    }

    public void setId(String id){this.id=id;}
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

    public void setContents(String contents) {
        this.contents = contents;
    }
}
