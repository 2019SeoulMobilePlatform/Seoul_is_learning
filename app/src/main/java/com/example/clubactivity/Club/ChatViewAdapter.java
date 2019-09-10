package com.example.clubactivity.Club;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clubactivity.R;

import java.util.ArrayList;

public class ChatViewAdapter extends BaseAdapter {
    public ArrayList<ChatViewItem> chatViewItemList;

    // ChatViewAdapter의 생성자
    public ChatViewAdapter() {
        chatViewItemList = new ArrayList<ChatViewItem>();
    }

    public ArrayList<ChatViewItem> getChatViewItemList(){ return chatViewItemList; }
    public void setChatViewItemList( ArrayList<ChatViewItem> list){ this.chatViewItemList = list;}


    @Override
    public int getCount() {
        return chatViewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return chatViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void removeAllItem() { chatViewItemList.clear(); }

    public void removeItem(int i) { chatViewItemList.remove(i); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple_list_item_1, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ChatViewItem chatViewItem = chatViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(chatViewItem.getIcon());
        titleTextView.setText(chatViewItem.getTitle());
        descTextView.setText(chatViewItem.getDesc());

        return convertView;
    }

    public void addItem(Drawable icon, String title, String desc, int maxMem, int nowMem, int index){
        ChatViewItem item = new ChatViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        item.setMaxMemberNum(maxMem);
        item.setNowMemberNum(nowMem);
        item.setItemIndex(index);
        chatViewItemList.add(item);
    }

    public void addItem(ChatViewItem item){
        chatViewItemList.add(item);
    }
}
