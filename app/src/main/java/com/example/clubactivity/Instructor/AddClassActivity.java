package com.example.clubactivity.Instructor;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.clubactivity.Constants;
import com.example.clubactivity.ImageProcessing;
import com.example.clubactivity.MainActivity;
import com.example.clubactivity.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class AddClassActivity extends AppCompatActivity {

    private Spinner limitSpinner;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Bitmap classImage;
    EditText className;
    EditText classDescription;
    EditText classPrice;
    EditText classAddress;
    EditText classTarget;
    Spinner classLimit;
    Spinner classResidence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        mDisplayDate = (TextView) findViewById(R.id.date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddClassActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = year + "/" + month + "/" + day;
                mDisplayDate.setText(date);
            }
        };

        mDisplayTime = (TextView) findViewById(R.id.time);
        mDisplayTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(AddClassActivity.this, mTimeSetListener, hour,
                        minute, false);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time = String.valueOf(hour) + ":" + String.valueOf(minute);
                mDisplayTime.setText(time);
            }
        };

        Button button = findViewById(R.id.select_img_btn);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try {
                    if (ActivityCompat.checkSelfPermission(AddClassActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddClassActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PICK_IMAGE);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, Constants.REQUEST_PICK_IMAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        classAddress = findViewById(R.id.class_address);
        final EditText classTarget = findViewById(R.id.class_target_user);
        Spinner classLimit = findViewById(R.id.limit_spinner);
        Spinner classResidence = findViewById(R.id.area_spinner);



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
                    return;
                }
                if (classAddress.length() == 0){
                    Toast.makeText(AddClassActivity.this, "상세주소를 입력해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (classTarget.length() == 0){
                    Toast.makeText(AddClassActivity.this, "수강 대상을 입력해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (mDisplayDate.getText().toString().equals("날짜") || mDisplayTime.getText().toString().equals("시간")){
                    Toast.makeText(AddClassActivity.this, "클래스 일정을 설정해 주세요", Toast.LENGTH_LONG).show();
                    return;
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

                ///요 근처에서 서버 보내면 될거같아요

                AddClassActivity.this.finish();
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_PICK_IMAGE){
            ImageView imageView = findViewById(R.id.user_image);

            ImageProcessing imageProcessing = new ImageProcessing(AddClassActivity.this);
            Uri imgUri = data.getData();
            imageProcessing.SetImage(imageView, imgUri);
            classImage = imageProcessing.ConvertUriToBitmap(imgUri);
        }
    }


    //이 엑티비티에서 뒤로가기를 눌렀을 때
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
