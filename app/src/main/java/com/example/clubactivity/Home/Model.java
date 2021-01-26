package com.example.clubactivity.Home;

public class Model {

    private int image;
    private String title;
    private String desc;

    private String area;
    private String people;
    private String location;
    private String date;
    private String peopleNumber;

    public Model(int image, String title, String desc ,String area, String people, String location, String date, String peopleNumber) {
        this.image = image;
        this.title = title;
        this.desc = desc;

        this.area = area;
        this.people = people;
        this.location = location;
        this.date = date;
        this.peopleNumber = peopleNumber;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getPeople() {
        return people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPeopleNumber() {
        return peopleNumber;
    }
    public void setPeopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber;
    }
}
