package com.example.clubactivity.Club;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.R;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        Intent intent = getIntent();

        ImageView imageView = findViewById(R.id.full_screen_image);

        ChatViewItem item = (ChatViewItem)intent.getExtras().get("item");

        byte[] bytes = intent.getByteArrayExtra("chatImage");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageView.setImageBitmap(bmp);

    }
}
