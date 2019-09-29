package com.example.clubactivity;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Club.MessageListAdapter;

import java.net.URLEncoder;
import java.util.ArrayList;

public class AppManager {

    private static AppManager instance = null;

    private AppManager(){

    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    String email = "";

    public void setEmail(String _email){this.email = _email;}
    public String getEmail(){return email;}

    private ArrayList<ChatViewItem> wholeClub_Adapter = null;
    private ArrayList<ChatViewItem> myClub_Adapter = null;
//    private ArrayList<MessageListAdapter.MessageContents> messages = null;

    public void setWholeClub_Adapter(ArrayList<ChatViewItem> wholeClub_Adapter){this.wholeClub_Adapter = wholeClub_Adapter;}
    public ArrayList<ChatViewItem> getWholeClub_Adapter() {return wholeClub_Adapter;}

    public void setMyClub_Adapter(ArrayList<ChatViewItem> myClub_Adapter){this.myClub_Adapter = myClub_Adapter;}
    public ArrayList<ChatViewItem> getMyClub_Adapter() {return myClub_Adapter;}

//    public void setMessages(ArrayList<MessageListAdapter.MessageContents> messages) {this.messages = messages;}
//    public ArrayList<MessageListAdapter.MessageContents> getMessages(){return messages;}

    public String encodeStr(String str){
        Log.d("스트링", str);
        if(str.contains("'"))
            str.replace("'", "");

        if(str.contains("&"))
            str = URLEncoder.encode(str);

        Log.d("스트링2", str);
        return str;
    }

    public Boolean isVailStr(String str){
        if(str.contains("&") || str.contains("'")){
            return false;
        }

        return true;
    }


    public Bitmap resize(Bitmap bitmap){
        Bitmap dstBitmap;
        if(bitmap.getWidth() > bitmap.getHeight())
            dstBitmap = Bitmap.createScaledBitmap(bitmap, Constants.IMAGE_SIZE, (bitmap.getHeight()*Constants.IMAGE_SIZE)/bitmap.getWidth(), true);
        else
            dstBitmap = Bitmap.createScaledBitmap(bitmap, (Constants.IMAGE_SIZE*bitmap.getWidth())/bitmap.getHeight(), Constants.IMAGE_SIZE, true);
        return dstBitmap;
    }
}
