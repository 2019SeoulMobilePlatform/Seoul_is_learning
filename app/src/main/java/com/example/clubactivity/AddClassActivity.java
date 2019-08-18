package com.example.clubactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.Club.AddClubActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddClassActivity extends AppCompatActivity {

    private Spinner limitSpinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Bitmap classImage;
    EditText className;
    EditText classDescription;
    EditText classPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        Button button = findViewById(R.id.select_img_btn);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), 1000);
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
        Button save_club_content = (Button) findViewById(R.id.save_class_content);

        //동호회 이름, 설명
        className = (EditText) findViewById(R.id.class_name);
        classDescription = (EditText) findViewById(R.id.class_description);
        classPrice = (EditText) findViewById(R.id.class_price);
        
        save_club_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(classImage == null ){
                    Toast.makeText(AddClassActivity.this, "이미지를 선택해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (className.length() == 0) {
                    Toast.makeText(AddClassActivity.this, "클래스 이름을 작성해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (classDescription.length() == 0) {
                    Toast.makeText(AddClassActivity.this, "클래스 설명을 작성해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (classPrice.length() == 0){
                    Toast.makeText(AddClassActivity.this, "클래스 금액을 입력해 주세요", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent();

                //바이트 어레이로 이미지 전송
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                classImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                intent.putExtra("BMP",bytes);

                //intent.putExtra("clubImage", userImage);
                intent.putExtra("className", className.getText().toString());
                intent.putExtra("classDescription", classDescription.getText().toString());
                intent.putExtra("classMaxMember", Integer.parseInt(limitSpinner.getSelectedItem().toString()));

                setResult(RESULT_OK, intent);
                AddClassActivity.this.finish();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1000){
            ImageView imageView = findViewById(R.id.user_image);
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                classImage = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(classImage);
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }


    //이 엑티비티에서 뒤로가기를 눌렀을 때
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
