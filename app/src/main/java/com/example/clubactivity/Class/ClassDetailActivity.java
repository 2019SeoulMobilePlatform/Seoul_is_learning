package com.example.clubactivity.Class;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.clubactivity.AppManager;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;
import com.google.android.material.tabs.TabLayout;

public class ClassDetailActivity extends AppCompatActivity {


    private ViewGroup viewLayout;         // 레이아웃
    private LayoutInflater inflater;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    static public String desc;
    static public String people;
    static public String location;
    static public String date;
    static public String number_now;
    static public String number;
    static public String price;
    static public String class_index;
    static public boolean favorite;

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
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        ImageView imageView = findViewById(R.id.class_main_image);

        //글라이드처리
        Glide.with(ClassDetailActivity.this)
                .load(byteArray)
                .into(imageView);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        imageView.setImageBitmap(bitmap);


        //지역구
        TextView textView1 = findViewById(R.id.class_area_intent);
        textView1.setText(getIntent().getStringExtra("area"));

        //별점
        RatingBar ratingBar = findViewById(R.id.review_total_star);
        ratingBar.setRating(getIntent().getFloatExtra("star", 5));


        desc = getIntent().getStringExtra("desc");
        people = getIntent().getStringExtra("people");
        location = getIntent().getStringExtra("location");
        date = getIntent().getStringExtra("date");
        number_now = getIntent().getStringExtra("number_now");
        number = getIntent().getStringExtra("number");
        price = getIntent().getStringExtra("price");
        favorite = getIntent().getBooleanExtra("favorite", false);

        class_index = String.valueOf(getIntent().getIntExtra("class_index",0));

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

        ImageButton heartImage = (ImageButton)findViewById(R.id.heart);

        if(favorite)
            heartImage.setImageResource(R.drawable.heart_red);
        else
            heartImage.setImageResource(R.drawable.heart_empty);
    }

    //클래스 예약 버튼 클릭
    public void ClassReservationButtonClicked(View view){
        if(getIntent().getExtras().get("is_instructor") != null &&(boolean)getIntent().getExtras().get("is_instructor") == true){
            Toast.makeText(ClassDetailActivity.this, "강사 계정의 클래스 예약은 불가능합니다.", Toast.LENGTH_SHORT);
            return;
       }


        Intent intent = new Intent(getApplicationContext(), ClassReservation.class);

        intent.putExtra("param", getIntent().getStringExtra("param")); //클래스 제목을 뿌려준다.
        //intent.putExtra("image",getIntent().getIntExtra("image",2)); //클래스 이미지 뿌리기

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        intent.putExtra("image",byteArray); //클래스 이미지 뿌리기

        intent.putExtra("price", getIntent().getStringExtra("price")); //가격뿌리기

        intent.putExtra("class_index", getIntent().getIntExtra("class_index",0)); //클래스인덱스뿌리기


        startActivity(intent);
    }

    //클래스 찜 버튼 클릭
    public void ClassHeartButtonClicked(View view){

        ImageButton heartImage = (ImageButton)findViewById(R.id.heart);

        Drawable temp1 = heartImage.getDrawable();
        Drawable temp2 = view.getResources().getDrawable(R.drawable.heart_empty);

        Bitmap tmp1Bitmap1 = ((BitmapDrawable)temp1).getBitmap();
        Bitmap tmp1Bitmap2 = ((BitmapDrawable)temp2).getBitmap();

        if(!Constants.isLogined){
            Toast.makeText(getApplicationContext(), "찜 기능은 로그인 후 이용 가능합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(tmp1Bitmap1.equals(tmp1Bitmap2))
        {
            heartImage.setImageResource(R.drawable.heart_red);

            String url = "http://106.10.35.170/AddFavoriteClass.php";
            String data = getData(getIntent().getIntExtra("class_index",0) , AppManager.getInstance().getEmail());

            NetworkTask networkTask = new NetworkTask(ClassDetailActivity.this, url, data, Constants.SERVER_CLASS_ADD_FAVORITE);
            networkTask.execute();
        }
        else
        {
            heartImage.setImageResource(R.drawable.heart_empty);
            String url = "http://106.10.35.170/DeleteFavoriteClass.php";
            String data = getData(getIntent().getIntExtra("class_index",0) , AppManager.getInstance().getEmail());

            NetworkTask networkTask = new NetworkTask(ClassDetailActivity.this, url, data, Constants.SERVER_CLASS_DELETE_FAVORITE);
            networkTask.execute();
        }

    }

    public String getData(int class_index, String email){

        String data = "class_index=" + class_index + "&email=" + email;

        return data;
    }




}
