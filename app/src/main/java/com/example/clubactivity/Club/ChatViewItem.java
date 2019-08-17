package com.example.clubactivity.Club;

import android.graphics.drawable.Drawable;

public class ChatViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private int maxMemberNum;
    private int nowMemberNum;

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

}
