package com.example.clubactivity.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.clubactivity.R;
import com.google.android.material.tabs.TabLayout;

public class ClassDetailActivity extends AppCompatActivity {


    private ViewGroup viewLayout;         // 레이아웃
    private LayoutInflater inflater;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflater = getLayoutInflater();
        viewLayout = (ViewGroup)inflater.inflate(R.layout.class_detail_page, null);
        setContentView(viewLayout);
        //setContentView(R.layout.class_detail_page);

        //클래스 제목 부분을 바꿔준다
        TextView textView = findViewById(R.id.class_name);
        textView.setText(getIntent().getStringExtra("param"));

        //이미지를 바꿔준다
        ImageView imageView = findViewById(R.id.class_main_image);
        imageView.setImageResource(getIntent().getIntExtra("image",2)); // 이 숫자는 뭘까..? 어쨌든 이렇게 하니 바뀌었다

        //지역구
        TextView textView1 = findViewById(R.id.class_area_intent);
        textView1.setText(getIntent().getStringExtra("area"));


        //탭


        tabLayout = findViewById(R.id.class_tabs);
       // tabLayout.addTab(tabLayout.newTab().setText("Tab one"));
       // tabLayout.addTab(tabLayout.newTab().setText("Tab two"));

        viewPager = findViewById(R.id.container_class);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }




    //클래스 예약 버튼 클릭
    public void ClassReservationButtonClicked(View view){
        Intent intent = new Intent(getApplicationContext(), ClassReservation.class);
        startActivity(intent);
    }

    //클래스 찜 버튼 클릭
    public void ClassHeartButtonClicked(View view){

        ImageButton heartImage = (ImageButton)findViewById(R.id.heart);

        if(true)
        {
            heartImage.setImageResource(R.drawable.heart_red);
            Toast.makeText(getApplicationContext(), "찜한 클래스에 추가되었습니다!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            heartImage.setImageResource(R.drawable.heart_empty);
            Toast.makeText(getApplicationContext(), "찜한 클래스에 해제되었습니다!", Toast.LENGTH_SHORT).show();
        }

    }




}
