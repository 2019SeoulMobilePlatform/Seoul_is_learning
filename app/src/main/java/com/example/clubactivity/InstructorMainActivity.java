package com.example.clubactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.clubactivity.Class.ClassDetailActivity;
import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Club.ClubEnterActivity;

public class InstructorMainActivity extends AppCompatActivity {

    ListView instructorClassList;
    ChatViewAdapter instructorClassAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);
        instructorClassList = findViewById(R.id.instructor_class_listview);
        instructorClassAdapter = new ChatViewAdapter() ;
        instructorClassList.setAdapter(instructorClassAdapter);

        instructorClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bitmap bitmap;
                Intent intent = new Intent(InstructorMainActivity.this, ClassDetailActivity.class);
                intent.putExtra("param", ((ChatViewItem)instructorClassAdapter.getItem(i)).getTitle());
                intent.putExtra("clubDescription", ((ChatViewItem)instructorClassAdapter.getItem(i)).getDesc());

                //startActivityForResult(intent, Constants.REQUEST_CLUB_INTRO_ENTER);
            }
        });


        instructorClassAdapter.addItem(ContextCompat.getDrawable(InstructorMainActivity.this, R.drawable.cat_dog),
                "일러스트 원데이 클래스", "성수역 부근 원데이 일러스트 클래스입니다", 10, 1) ;
        instructorClassAdapter.addItem(ContextCompat.getDrawable(InstructorMainActivity.this, R.drawable.class_paint),
                "유화 그리기", "50,000",10,1) ;


    }




}
