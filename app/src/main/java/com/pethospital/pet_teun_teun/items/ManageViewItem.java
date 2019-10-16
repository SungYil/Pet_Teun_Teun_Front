package com.pethospital.pet_teun_teun.items;

import android.graphics.drawable.Drawable;

import com.pethospital.pet_teun_teun.R;

public class ManageViewItem {
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
    public ManageViewItem(Drawable icon, String name, String subname, String content) {
        this.icon = icon;
        this.name = name;
        this.subname = subname;
        this.content = content;
    }

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
}
