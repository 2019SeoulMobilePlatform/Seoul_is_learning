package com.example.clubactivity.Club;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.clubactivity.AppManager;
import com.example.clubactivity.Class.ClassDetailActivity;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ClubEnterActivity extends AppCompatActivity {
    TextView title;
    int room_index;
    ChatViewItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_enter);

        Intent intent = getIntent();

        ImageView clubImage = findViewById(R.id.club_enter_image);
        title = findViewById(R.id.club_name);
        TextView description = findViewById(R.id.club_description);
        TextView memberNumber = findViewById(R.id.club_memeber_number);


        item = (ChatViewItem)intent.getExtras().get("item");


        byte[] bytes = intent.getByteArrayExtra("clubImage");
        Glide.with(ClubEnterActivity.this)
                .load(bytes)
                .into(clubImage);
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        clubImage.setImageBitmap(bmp);
/*
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), (Uri)intent.getExtras().get("imageUri"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
*/
        //clubImage.setImageBitmap(((BitmapDrawable)item.getIcon()).getBitmap());
        //clubImage.setImageBitmap(bitmap);
        title.setText(intent.getExtras().getString("clubName"));
        room_index = intent.getExtras().getInt("clubIndex");
        description.setText(intent.getExtras().getString("clubDescription"));
        memberNumber.setText(intent.getExtras().get("clubNowMember").toString() + "/" + intent.getExtras().get("clubMaxMember").toString() + "명");
    }

    public void JoinClub(View view) {
        //서버 유저 동호회 목록에 추가, 내 동호회 목록은 서버에서 받아서 업데이트 해야할듯
        //TODO: 인텐트에서 넘어온 clubIndex 사용해서 서버 업데이트 할 것
        //서버업데이트는 하는데 자체적으로 리스트에 추가해주어야함
        String url = "http://106.10.35.170/JoinClub.php";
        String data = getData(AppManager.getInstance().getEmail(), room_index);
        NetworkTask networkTask = new NetworkTask(this,url,data,9);
        networkTask.execute();

        //서버안통하고 자체적으로 리스트에 추가하는 메소드

        Intent intent = new Intent(ClubEnterActivity.this, ChatRoomActivity.class);
        intent.putExtra("clubName", title.getText());
        startActivityForResult(intent, Constants.REQUEST_CLUB_ENTER);
        this.finish();
    }

    public String getData(String email, int room_index){
        String data = "email=" + email + "&room_index=" + room_index;

        return data;
    }

    public void setMyClubList(){
        ArrayList<ChatViewItem> myClubList = new ArrayList<ChatViewItem>();
        myClubList = AppManager.getInstance().getMyClub_Adapter();
        myClubList.add(item);

        AppManager.getInstance().setMyClub_Adapter(myClubList);
    }
}
