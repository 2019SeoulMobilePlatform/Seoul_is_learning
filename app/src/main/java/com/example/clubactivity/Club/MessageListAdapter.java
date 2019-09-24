package com.example.clubactivity.Club;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
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
import com.example.clubactivity.Constants;
import com.example.clubactivity.R;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MessageContents> messages;



    public MessageListAdapter(Context context, ArrayList<MessageContents> messages){
        this.messages = messages;
        this.context = context;
    }

//    public MessageListAdapter(Context context){
//        this.messages = new ArrayList<>();
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    public Bitmap getImage(int i) {return messages.get(i).image; }

    public ArrayList<MessageContents> getMessages() {return messages;}

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //텍스트 전송
    public void addItem(String text, int type, String from_id, String nickname, Bitmap profileImage) {
        if(type == 0) {
            messages.add(new MessageContents(text, type, from_id));
        }
        else if(type == 1){
            //TODO
            //서버에서 id에 해당하는 닉네임, 프로필 이미지 가져와서 넣어야됨
            messages.add(new MessageContents(text, type, from_id, nickname, profileImage));
        }
    }
    public void addItem(String text, int type,String from_id){
        messages.add(new MessageContents(text, type, from_id));
    }
    public void addItem(int type,Bitmap image, String from_id){
        messages.add(new MessageContents(type, from_id, image));
    }

    //이미지 전송시 url로 받을 경우
    //public void addItem(int type, String imagePath, String from_id) { messages.add(new MessageContents(type, from_id, imagePath, null));}

    //이미지 Bitmap으로 받을 경우
    public void addItem(int type, String nickname, String from_id,  Bitmap image, Bitmap profileImage) {
        if(type == 0) {
            messages.add(new MessageContents(type, from_id, image));
        }
        else if(type == 1){
            //TODO
            //서버에서 id에 해당하는 닉네임, 프로필 이미지 가져와서 넣어야됨
            Log.e("대화받는중", "이미지ing..");
            messages.add(new MessageContents(type, from_id, nickname, profileImage, image));
        }
    }



    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        CustomHolder holder = null;
        TextView text = null;
        LinearLayout layout = null;
        FrameLayout frameLayout = null;
        ImageView imageView = null;
        CircleImageView circleImageView = null;
        TextView nickTextView = null;


        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_message_box, viewGroup, false);

            layout = (LinearLayout)view.findViewById(R.id.chat_message_layout);
            text = (TextView)view.findViewById(R.id.message_text);
            frameLayout = (FrameLayout) view.findViewById(R.id.chat_message_box_layout);
            imageView = (ImageView) view.findViewById(R.id.chat_image);
            circleImageView = (CircleImageView) view.findViewById(R.id.chat_user_circleimage);
            nickTextView = (TextView) view.findViewById(R.id.chat_user_id);


            holder = new CustomHolder();
            holder.m_TextView = text;
            holder.layout = layout;
            holder.m_FrameLayout = frameLayout;
            holder.m_ImageView = imageView;
            holder.m_nicknameTextView = nickTextView;
            holder.m_profileImageView = circleImageView;
            view.setTag(holder);

        } else{
            holder = (CustomHolder) view.getTag();
            text = holder.m_TextView;
            layout = holder.layout;
            frameLayout = holder.m_FrameLayout;
            imageView = holder.m_ImageView;
            circleImageView = holder.m_profileImageView;
            nickTextView = holder.m_nicknameTextView;
        }


        text.setText(messages.get(i).msg);

        //frameLayout.getLayoutParams().width = text.getWidth();
        imageView.setImageBitmap(messages.get(i).image);
        //Glide.with(context).asBitmap().load(messages.get(i).path).into(imageView);

        //내가 보낸건지 남이 보낸건지에 따라 위치밑 상세 설정
        if( messages.get(i).type == 0 ) {
            //내가보낸거
            frameLayout.setBackgroundResource(R.drawable.chat_message_box_i);
            text.setTextColor(Color.BLACK);
            layout.setGravity(Gravity.RIGHT);
            nickTextView.setText("");
            circleImageView.setImageBitmap(null);
        }
        if(messages.get(i).type == 1){
            frameLayout.setBackgroundResource(R.drawable.chat_message_box_you);
            text.setTextColor(Color.BLACK);
            layout.setGravity(Gravity.LEFT);
            //from_id(보낸 사람 식별번호)에 따라 닉네임과 프로필 이미지를 서버에서 가져와 넣어줘야함
            nickTextView.setText(messages.get(i).getNickName());
            circleImageView.setImageBitmap(messages.get(i).getProfileImage());
        }

        //이미지뷰 크기 설정
        if(messages.get(i).image != null){
            imageView.getLayoutParams().height = 500;
            imageView.getLayoutParams().width = 500;
            final Bitmap image = messages.get(i).image;

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FullScreenImageActivity.class);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap dstBitmap = Bitmap.createScaledBitmap(image, Constants.IMAGE_SIZE, image.getHeight()/(image.getWidth()/Constants.IMAGE_SIZE), true);

                    dstBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] bytes = stream.toByteArray();
                    intent.putExtra("chatImage",bytes);

                    context.startActivity(intent);
                }
            });

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
        CircleImageView m_profileImageView;
        TextView m_nicknameTextView;
    }

}
