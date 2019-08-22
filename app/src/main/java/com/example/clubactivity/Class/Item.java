package com.example.clubactivity.Class;

public class Item {
    private int image;
    private String title;
    private String area;

    private String desc;
    private String people;
    private String location;
    private String date;
    private String peopleNumber;

    public int getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getArea() {
        return area;
    }

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

    Item(int image, String title, String desc ,String area, String people, String location, String date, String peopleNumber) {
        this.image = image;
        this.title = title;
        this.desc = desc;

        this.area = area;
        this.people = people;
        this.location = location;
        this.date = date;
        this.peopleNumber = peopleNumber;
    }

}
