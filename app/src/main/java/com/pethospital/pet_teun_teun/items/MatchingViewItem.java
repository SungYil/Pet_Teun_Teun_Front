package com.pethospital.pet_teun_teun.items;

public class MatchingViewItem {
    private String name;
    private String km;
    private String phoneNum;
    /**
     * 오픈타임 아니다 바꿔야한다.
     */
    private String openTime;
    private int rating;

    public MatchingViewItem(){
        this.name="emp";
        this.km="0km";
        this.phoneNum="010-xxx-xxxx";
        this.openTime="10시";
        this.rating=1;
    }
    public MatchingViewItem(String name, String km, String phoneNum, String openTime, int rating) {
        this.name = name;
        this.km = km;
        this.phoneNum = phoneNum;
        this.openTime = openTime;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getKm() {
        return km;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getOpenTime() {
        return openTime;
    }

    public int getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
