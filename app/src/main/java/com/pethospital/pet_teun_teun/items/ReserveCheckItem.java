package com.pethospital.pet_teun_teun.items;

public class ReserveCheckItem {
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
}
