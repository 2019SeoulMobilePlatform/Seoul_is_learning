package com.example.clubactivity;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter {

    public class MessageContents{
        String msg;
        int type;
        private String from_id;
        private String to_id;
        private String datetime;

        public MessageContents(String _msg,int _type, String _from_id, String _to_id, String _dateTime){
            this.msg = _msg;
            this.type = _type;
            this.datetime = _dateTime;
            this.from_id = _from_id;
            this.to_id = _to_id;
        }
    }

    ArrayList<MessageContents> messages;

    public MessageListAdapter(){
        messages = new ArrayList<MessageContents>();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void addItem(String text, int type) { messages.add(new MessageContents(text, type, "0", "0", "0"));}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        CustomHolder holder = null;
        TextView text = null;
        LinearLayout layout = null;
        View viewRight = null;
        View viewLeft = null;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_message_box, viewGroup, false);

            layout = (LinearLayout)view.findViewById(R.id.chat_message_layout);
            text = (TextView)view.findViewById(R.id.message_text);

            holder = new CustomHolder();
            holder.m_TextView = text;
            holder.layout = layout;
            view.setTag(holder);

        } else{
            holder = (CustomHolder) view.getTag();
            text = holder.m_TextView;
            layout = holder.layout;
        }

        text.setText(messages.get(i).msg);

        if( messages.get(i).type == 0 ) {
            text.setBackgroundResource(R.drawable.chat_message_box_i);
            text.setTextColor(Color.BLACK);
            layout.setGravity(Gravity.RIGHT);
        }
        if(messages.get(i).type == 1){
            text.setBackgroundResource(R.drawable.chat_message_box_you);
            text.setTextColor(Color.BLACK);
            layout.setGravity(Gravity.LEFT);
        }

        return view;
    }

    private class CustomHolder {
        TextView    m_TextView;
        LinearLayout layout;
    }

}
