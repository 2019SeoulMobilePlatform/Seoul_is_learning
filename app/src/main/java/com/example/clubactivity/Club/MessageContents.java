package com.example.clubactivity.Club;

import android.graphics.Bitmap;

public class MessageContents {

    String msg;
    int type;
    private String from_id;
    //private String path;
    private Bitmap profileImage;
    Bitmap image;
    private String nickName;

    public MessageContents(String _msg,int _type, String _from_id){
        this.msg = _msg;
        this.type = _type;
        this.from_id = _from_id;
        //this.path = null;
        this.image = null;
        this.profileImage = null;
    }

    public MessageContents(String _msg, int _type, String _from_id, String nickName, Bitmap profileImage){
        this.msg = _msg;
        this.type = _type;
        this.from_id = _from_id;
        //this.path = null;
        this.image = null;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    public MessageContents(int _type, String _from_id, Bitmap image){
        this.msg = "";
        this.type = _type;
        this.from_id = _from_id;
        //this.path = null;
        this.profileImage = null;
        this.image = image;
    }

    public MessageContents(int _type, String _from_id, String nickName, Bitmap profileImage, Bitmap image){
        this.msg = "";
        this.type = _type;
        this.from_id = _from_id;
        //this.path = null;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.image = image;
    }

    /*
    public MessageContents(int type, String _from_id, String imagePath, Bitmap image){
        this.type = type;
        this.from_id = _from_id;
        //this.path = imagePath;
        this.image = image;
    }*/
    public String getNickName() {return this.nickName;}
    public Bitmap getProfileImage() {return this.profileImage;}
}


