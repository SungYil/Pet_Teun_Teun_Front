package com.pethospital.pet_teun_teun.items;

public class AlarmItem {
    private String id;
    private String imgUrl;
    private String title;
    private String contents;
    private String date;

    public AlarmItem() {

    }

    public AlarmItem(AlarmItem item){
        this(item.getId(),item.getImgUrl(),item.getTitle(),item.getContents(),item.getDate());
    }
    public AlarmItem(String id,String imgUrl,String title,String contents,String date){
        this.id=id;
        this.imgUrl=imgUrl;
        this.title=title;
        this.contents=contents;
        this.date=date;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
