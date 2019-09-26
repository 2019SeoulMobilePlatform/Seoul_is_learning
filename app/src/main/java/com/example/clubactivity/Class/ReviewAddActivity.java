package com.example.clubactivity.Class;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;


/**
 * 후기 작성 페이지
 */
public class ReviewAddActivity extends AppCompatActivity implements View.OnClickListener {

    RatingBar ratingBar;    // 별점 바
    EditText reviewEditText;    // 후기 내용
    Button completingReviewButton;  // 작성완료 버튼
    Double starNum;  // 별점
    String reviewContent;   // 후기 내용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add);

        ratingBar = findViewById(R.id.star_ratingbar);
        reviewEditText = findViewById(R.id.review_content2);
        completingReviewButton = findViewById(R.id.completing_review_button);
        completingReviewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 작성완료 버튼 누를 시 이벤트

            case R.id.completing_review_button:
                //서버 연동
                if(reviewEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"후기 글을 작성해주세요", Toast.LENGTH_SHORT).show();
                } else{

                    //네트워크 연결
                    String url = "http://106.10.35.170/AddReview.php";
                    String data = getData();
                    NetworkTask networkTask = new NetworkTask(ReviewAddActivity.this, url, data, Constants.SERVER_CLASS_ADD_REVIEW);
                    networkTask.execute();

                    finish();
                }
        }
    }

    //후기 데이터 서버로 보내기
    public String getData(){
        starNum = Double.valueOf(ratingBar.getRating());
        String data = "class_index=" + ClassDetailActivity.class_index + "&review=" + reviewEditText.getText().toString().trim() + "&user_name=" + AppManager.getInstance().getEmail() + "&star=" +  String.valueOf(starNum);
        return data;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(0);
        finish();
    }

}
