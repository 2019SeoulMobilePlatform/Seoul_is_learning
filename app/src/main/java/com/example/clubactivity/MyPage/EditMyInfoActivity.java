package com.example.clubactivity.MyPage;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), 1007);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1007){
            CircleImageView imageView = findViewById(R.id.user_image);
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap userImage = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(userImage);
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
