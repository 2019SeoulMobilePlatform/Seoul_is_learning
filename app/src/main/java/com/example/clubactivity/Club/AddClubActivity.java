package com.example.clubactivity.Club;

//import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.clubactivity.AddClassActivity;
import com.example.clubactivity.Constants;
import com.example.clubactivity.ImageProcessing;
import com.example.clubactivity.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddClubActivity extends AppCompatActivity {

    private Spinner limitSpinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Bitmap userImage;
    EditText clubName;
    EditText clubDescription;
    Uri imgUri;
    ImageProcessing imageProcessing = new ImageProcessing(AddClubActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_club);

        Button button = findViewById(R.id.select_img_btn);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("image/*");

                //startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), 1000);
                try {
                    if (ActivityCompat.checkSelfPermission(AddClubActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddClubActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PICK_IMAGE);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, Constants.REQUEST_PICK_IMAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //startActivityForResult(gallery, Constants.REQUEST_PICK_IMAGE);
            }
        });

        arrayList = new ArrayList<>();
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        limitSpinner = (Spinner)findViewById(R.id.limit_spinner);
        limitSpinner.setAdapter(arrayAdapter);

        //저장 버튼
        Button save_club_content = (Button) findViewById(R.id.save_club_content);

        //동호회 이름, 설명
        clubName = (EditText) findViewById(R.id.club_name);
        clubDescription = (EditText) findViewById(R.id.description);



        save_club_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imgUri == null ){
                    Toast.makeText(AddClubActivity.this, "이미지를 선택해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (clubName.length() == 0) {
                    Toast.makeText(AddClubActivity.this, "동호회 이름을 작성해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (clubDescription.length() == 0) {
                    Toast.makeText(AddClubActivity.this, "동호회 설명을 작성해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();

                //바이트 어레이로 이미지 전송
                userImage = imageProcessing.ConvertUriToBitmap(imgUri);

                byte[] bytes = imageProcessing.ConvertBitmapToByteArray(userImage);

                intent.putExtra("BMP",bytes);
                intent.putExtra("clubName", clubName.getText().toString());
                intent.putExtra("clubDescription", clubDescription.getText().toString());
                intent.putExtra("clubMaxMember", Integer.parseInt(limitSpinner.getSelectedItem().toString()));

                setResult(RESULT_OK, intent);
                AddClubActivity.this.finish();
            }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_PICK_IMAGE){
            ImageView imageView = findViewById(R.id.user_image);
            /* 회전 고치기전 코드
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                userImage = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(userImage);
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            */

            imgUri = data.getData();
            imageProcessing.SetImage(imageView, imgUri);
        }
    }


    //이 엑티비티에서 뒤로가기를 눌렀을 때
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }


}
