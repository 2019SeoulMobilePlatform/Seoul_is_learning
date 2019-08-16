package com.example.clubactivity.Club;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clubactivity.R;

import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter {

    Context context;

    public class MessageContents{
        String msg;
        int type;
        private String from_id;
        private String to_id;
        private String datetime;
        private String path;
        Bitmap image;


        public MessageContents(String _msg,int _type, String _from_id, String _to_id, String _dateTime, Bitmap image){
            this.msg = _msg;
            this.type = _type;
            this.datetime = _dateTime;
            this.from_id = _from_id;
            this.to_id = _to_id;
            this.path = null;
            this.image = image;
        }

        public MessageContents(int type, String _from_id, String imagePath, Bitmap image){
            this.type = type;
            this.from_id = _from_id;
            this.path = imagePath;
            this.image = image;
        }
    }

    ArrayList<MessageContents> messages;

    public MessageListAdapter(Context context){
        messages = new ArrayList<MessageContents>();
        this.context = context;
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

    public void addItem(String text, int type) { messages.add(new MessageContents(text, type, "0", "0", "0", null));}

    public void addItem(int type, String imagePath) { messages.add(new MessageContents(type, "0", imagePath, null));}

    public void addItem(int type, Bitmap image) { messages.add(new MessageContents(type, "0", "", image));}



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        CustomHolder holder = null;
        TextView text = null;
        LinearLayout layout = null;
        FrameLayout frameLayout = null;
        ImageView imageView = null;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_message_box, viewGroup, false);

            layout = (LinearLayout)view.findViewById(R.id.chat_message_layout);
            text = (TextView)view.findViewById(R.id.message_text);
            frameLayout = (FrameLayout) view.findViewById(R.id.chat_message_box_layout);
            imageView = (ImageView) view.findViewById(R.id.chat_image);


            holder = new CustomHolder();
            holder.m_TextView = text;
            holder.layout = layout;
            holder.m_FrameLayout = frameLayout;
            holder.m_ImageView = imageView;
            view.setTag(holder);

        } else{
            holder = (CustomHolder) view.getTag();
            text = holder.m_TextView;
            layout = holder.layout;
            frameLayout = holder.m_FrameLayout;
            imageView = holder.m_ImageView;
        }


        text.setText(messages.get(i).msg);
        //frameLayout.getLayoutParams().width = text.getWidth();
        imageView.setImageBitmap(messages.get(i).image);
        //Glide.with(context).asBitmap().load(messages.get(i).path).into(imageView);

        if( messages.get(i).type == 0 ) {
            frameLayout.setBackgroundResource(R.drawable.chat_message_box_i);
            text.setTextColor(Color.BLACK);
            layout.setGravity(Gravity.RIGHT);
        }
        if(messages.get(i).type == 1){
            frameLayout.setBackgroundResource(R.drawable.chat_message_box_you);
            text.setTextColor(Color.BLACK);
            layout.setGravity(Gravity.LEFT);
        }

        if(messages.get(i).path != null){
            imageView.getLayoutParams().height = 300;
            imageView.getLayoutParams().width = 300;
            frameLayout.setBackgroundResource(0);
        }
        else{
            imageView.getLayoutParams().height = text.getHeight();
            imageView.getLayoutParams().width = text.getWidth();
        }

        return view;
    }

    private class CustomHolder {
        TextView    m_TextView;
        FrameLayout m_FrameLayout;
        LinearLayout layout;
        ImageView m_ImageView;
    }

}
