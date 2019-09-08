package com.example.clubactivity.Class;

import android.graphics.Bitmap;

public class Item {
    private Bitmap image;
    private String title;
    private String area;
    private float star;

    private String desc;
    private String people;
    private String location;
    private String date;
    private String peopleNumber;

    public Bitmap getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getArea() {
        return area;
    }
    public float getStar(){return this.star;}

    public String getDesc() { return desc; }
    public String getPeople() {
        return people;
    }
    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
    public String getPeopleNumber() {
        return peopleNumber;
    }

    public Item(Bitmap image,float star, String title, String desc ,String area, String people, String location, String date, String peopleNumber) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.star = star;

        this.area = area;
        this.people = people;
        this.location = location;
        this.date = date;
        this.peopleNumber = peopleNumber;
    }

}
