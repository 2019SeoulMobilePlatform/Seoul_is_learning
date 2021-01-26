package com.example.clubactivity.Class;

public class Item {
    private byte[] image;
    private String title;
    private String area;
    private float star;

    private String desc;
    private String people;
    private String location;
    private String date;
    private String peopleNumberNow;
    private String peopleNumber;
    private String price;
    private Boolean favorite;

    private int class_index;
    private int flag_dongnae;

    public byte[] getImage() {
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
    public String getPeopleNumberNow(){return peopleNumberNow;}
    public String getPeopleNumber() {
        return peopleNumber;
    }
    public String getPrice() {
        return price;
    }
    public int getClass_index() {return class_index;}

    public int getFlag_dongnae() {return flag_dongnae;}
    public Boolean getFavorite() {return favorite;}

    public Item(int class_index ,byte[] image,float star, String title, String desc ,String area, String people, String location, String date,String peopleNumberNow ,String peopleNumber, String price, boolean favorite, int flag_dongnae) {

        this.class_index = class_index;
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.star = star;

        this.area = area;
        this.people = people;
        this.location = location;
        this.date = date;
        this.peopleNumberNow = peopleNumberNow;
        this.peopleNumber = peopleNumber;
        this.price = price;
        this.flag_dongnae = flag_dongnae;
        this.favorite = favorite;
    }

}
