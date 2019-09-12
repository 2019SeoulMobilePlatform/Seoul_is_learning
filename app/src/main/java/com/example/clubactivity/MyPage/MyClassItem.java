package com.example.clubactivity.MyPage;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;


public class MyClassItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private int maxMemberNum;
    private int nowMemberNum;
    private int itemIndex;

    private byte[] image;
    private String area;
    private float star;
    private String targetUser;
    private String location;
    private String maxNum;
    private String date;
    private String price;
    private Boolean favorite;

    private int flag_dongnae;


    public MyClassItem(int class_index ,byte[] image,float star, String title, String desc ,String area, String targetUser, String location, String date, String peopleNumber, String price, boolean favorite, int flag_dongnae) {

        this.itemIndex = class_index;
        this.image = image;
        this.titleStr = title;
        this.descStr = desc;
        this.star = star;

        this.area = area;
        this.targetUser = targetUser;
        this.location = location;
        this.date = date;
        this.maxNum = peopleNumber;
        this.price = price;
        this.flag_dongnae = flag_dongnae;
        this.favorite = favorite;
        this.iconDrawable = new BitmapDrawable(BitmapFactory.decodeByteArray(image, 0, image.length));
    }

    protected MyClassItem(Parcel in) {
        titleStr = in.readString();
        descStr = in.readString();
        maxMemberNum = in.readInt();
        nowMemberNum = in.readInt();
    }

    public MyClassItem() {
    }

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setMaxMemberNum(int num) {maxMemberNum = num;}
    public void setNowMemberNum(int num) {nowMemberNum = num;}
    public void setItemIndex(int num) {itemIndex = num;}


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public int getMaxMemberNum() {return maxMemberNum;}
    public int getNowMemberNum() {return nowMemberNum;}
    public int getItemIndex() {return itemIndex;}
    public byte[] getImage() {
        return this.image;
    }
    public String getArea() {
        return area;
    }
    public float getStar(){return this.star;}

    public String getPeople() {
        return targetUser;
    }
    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
    public String getPeopleNumber() {
        return maxNum;
    }
    public String getPrice() {
        return price;
    }
    public int getClass_index() {return itemIndex;}

    public int getFlag_dongnae() {return flag_dongnae;}
    public Boolean getFavorite() {return favorite;}



}
