package com.example.clubactivity.Class;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clubactivity.R;

import java.util.ArrayList;

public class ClassDetailActivity extends Activity {

    private ReviewListViewAdapter adapter;  //후기 리스트뷰 어댑터
    private ListView reviewList;         // 후기 리스트
    private ArrayList<ReviewListItem> reviewData;   // 후기 데이터
    private ViewGroup viewLayout;         // 레이아웃
    private LayoutInflater inflater;


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


        //탭뷰
        TabHost tabHost = (TabHost) findViewById(R.id.tapHost_class_detail) ;
        tabHost.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1_class_detail) ;
        ts1.setIndicator("소개") ;
        tabHost.addTab(ts1)  ;


        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2_class_review) ;
        ts2.setIndicator("후기") ;
        tabHost.addTab(ts2) ;


        setReviewPage();

        adapter = new ReviewListViewAdapter(inflater, R.layout.class_list_item, reviewData);

        reviewData.clear();

        reviewList.setAdapter(adapter);

        /*Button reviewButton = (Button)findViewById(R.id.class_review_button);
        reviewButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"클래스를 들은 회원만 작성 가능합니다", Toast.LENGTH_LONG).show();
            }
        });*/

    }


    //후기 작성 버튼
    public void ReviewButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();

    }


    // 리뷰페이지 설정하는 메소드
    public void setReviewPage() {
        // 후기 리스트 설정
        reviewList = (ListView) findViewById(R.id.class_review_listView);

        // 후기 데이터 설정
        reviewData = new ArrayList<>();

        // 어댑터로 후기 리스트에 아이템 뿌려주기
        adapter = new ReviewListViewAdapter(inflater, R.layout.class_list_item, reviewData);
        reviewList.setAdapter(adapter);

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
