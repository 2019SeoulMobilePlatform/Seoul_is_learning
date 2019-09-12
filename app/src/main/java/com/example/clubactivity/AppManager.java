package com.example.clubactivity;

import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Club.MessageListAdapter;

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
    private ArrayList<MessageListAdapter.MessageContents> messages = null;

    public void setWholeClub_Adapter(ArrayList<ChatViewItem> wholeClub_Adapter){this.wholeClub_Adapter = wholeClub_Adapter;}
    public ArrayList<ChatViewItem> getWholeClub_Adapter() {return wholeClub_Adapter;}

    public void setMyClub_Adapter(ArrayList<ChatViewItem> myClub_Adapter){this.myClub_Adapter = myClub_Adapter;}
    public ArrayList<ChatViewItem> getMyClub_Adapter() {return myClub_Adapter;}

    public void setMessages(ArrayList<MessageListAdapter.MessageContents> messages) {this.messages = messages;}
    public ArrayList<MessageListAdapter.MessageContents> getMessages(){return messages;}

}
