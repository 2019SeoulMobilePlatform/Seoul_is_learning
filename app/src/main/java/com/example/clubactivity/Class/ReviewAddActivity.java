package com.example.clubactivity.Class;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 후기 작성 페이지
 */
public class ReviewAddActivity extends AppCompatActivity implements View.OnClickListener {

    RatingBar ratingBar;    // 별점 바
    ImageView reviewImageView;  // 후기사진 이미지뷰
    EditText reviewEditText;    // 후기 내용
    Button completingReviewButton;  // 작성완료 버튼
    float starNum;  // 별점
    String reviewContent;   // 후기 내용

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add);

        ratingBar = findViewById(R.id.star_ratingbar);
        reviewEditText = findViewById(R.id.review_content);
        completingReviewButton = findViewById(R.id.completing_review_button);

        completingReviewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 작성완료 버튼 누를 시 이벤트

            case R.id.completing_review_button:
                //서버 연동
                if(reviewEditText.getText().toString().equals("")){
                    Toast.makeText(this,"후기를 글귀를 작성해주세요", Toast.LENGTH_LONG);
                } else{

                    /*네트워크 연결
                    networkTask.execute();*/
                }
        }
    }


    //후기 데이터 서버에 보내기 위한 JSON 형식 데이터
    private JSONObject sendJSonData() {

        JSONObject jsonObject = new JSONObject();

        Drawable reviewImage = reviewImageView.getDrawable();

        String profile_image = preferences.getString("profileImage","");

        starNum = (float) ratingBar.getRating();

        try {
            jsonObject.accumulate("user_image",profile_image);
            jsonObject.accumulate("number", "1");
            jsonObject.accumulate("nickname", preferences.getString("nickname",""));
            jsonObject.accumulate("point", String.valueOf(starNum));
            jsonObject.accumulate("content",reviewEditText.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(0);
        finish();
    }

}
