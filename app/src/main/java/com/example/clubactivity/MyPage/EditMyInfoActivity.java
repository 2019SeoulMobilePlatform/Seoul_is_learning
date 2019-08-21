package com.example.clubactivity.MyPage;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.clubactivity.Club.AddClubActivity;
import com.example.clubactivity.Constants;
import com.example.clubactivity.ImageProcessing;
import com.example.clubactivity.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditMyInfoActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_info);

        Button editButton = (Button) findViewById(R.id.edit_info_btn);
        EditText _nickname = (EditText) findViewById(R.id.edit_nickname);
        EditText _phonenumber = (EditText)findViewById(R.id.edit_phone);
        EditText _email = (EditText)findViewById(R.id.edit_email);

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        editor = preferences.edit();

        _nickname.setText(preferences.getString("nickname", ""));
        _phonenumber.setText(preferences.getString("phone_number", ""));
        _email.setText(preferences.getString("email",""));

        final EditText password = (EditText) findViewById(R.id.edit_password);
        final EditText passwordCheck = (EditText) findViewById(R.id.edit_password_check);

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals(passwordCheck.getText().toString())){
                    Toast.makeText(EditMyInfoActivity.this, "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG);
                    return;
                }

                Intent intent = new Intent();


                //intent.putExtra("password", .getText().toString());
                //intent.putExtra("nickname", .getText().toString());

                setResult(RESULT_OK, intent);
                EditMyInfoActivity.this.finish();
            }
        });

    }

    public void SetProfileImage(View view) {
        try {
            if (ActivityCompat.checkSelfPermission(EditMyInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EditMyInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PICK_IMAGE);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, Constants.REQUEST_PICK_IMAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_PICK_IMAGE){
            CircleImageView imageView = findViewById(R.id.user_image);

            ImageProcessing imageProcessing = new ImageProcessing(EditMyInfoActivity.this);
            Uri imgUri = data.getData();
            imageProcessing.SetImage(imageView, imgUri);

        }
    }
}
