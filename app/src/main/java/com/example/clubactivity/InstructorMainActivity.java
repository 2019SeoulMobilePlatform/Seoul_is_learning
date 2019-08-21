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
import com.example.clubactivity.Club.AddClubActivity;
import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Club.ClubEnterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
                //Intent intent = new Intent(context, TabTest.class);

                intent.putExtra("param", ((ChatViewItem)instructorClassAdapter.getItem(i)).getTitle());
                intent.putExtra("clubDescription", ((ChatViewItem)instructorClassAdapter.getItem(i)).getDesc());

                //intent.putExtra("image",((ChatViewItem)instructorClassAdapter.getItem(i)).getIcon()); //클래스 이미지 뿌리기
                //디테일 비트맵으로 받으면 수정할것. 임시로 넣어놓겠다.
                intent.putExtra("image", R.drawable.class1);

                InstructorMainActivity.this.startActivity(intent);

                //startActivityForResult(intent, Constants.REQUEST_CLUB_INTRO_ENTER);
            }
        });


        instructorClassAdapter.addItem(ContextCompat.getDrawable(InstructorMainActivity.this, R.drawable.class1),
                "원데이 클래스", "20,000", 10, 1) ;
        instructorClassAdapter.addItem(ContextCompat.getDrawable(InstructorMainActivity.this, R.drawable.class_paint),
                "유화 그리기", "50,000",10,1) ;


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_class_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorMainActivity.this, AddClassActivity.class);
                startActivityForResult(intent, Constants.REQUSET_ADD_CLASS); // 요청한 곳을 구분하기 위한 숫자, 의미없음
            }
        });

    }




}
