package com.example.clubactivity.Club;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class ChatViewItem implements Parcelable {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private int maxMemberNum;
    private int nowMemberNum;
    private int itemIndex;

    protected ChatViewItem(Parcel in) {
        titleStr = in.readString();
        descStr = in.readString();
        maxMemberNum = in.readInt();
        nowMemberNum = in.readInt();
    }

    public static final Creator<ChatViewItem> CREATOR = new Creator<ChatViewItem>() {
        @Override
        public ChatViewItem createFromParcel(Parcel in) {
            return new ChatViewItem(in);
        }

        @Override
        public ChatViewItem[] newArray(int size) {
            return new ChatViewItem[size];
        }
    };

    public ChatViewItem() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titleStr);
        parcel.writeString(descStr);
        parcel.writeInt(maxMemberNum);
        parcel.writeInt(nowMemberNum);
    }
}
