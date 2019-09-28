package com.example.clubactivity.Class;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

import java.util.ArrayList;

public class ClassDetailReview extends Fragment {

    private ReviewListViewAdapter adapter;  //후기 리스트뷰 어댑터
    private ListView reviewList;         // 후기 리스트
    private ArrayList<ReviewListItem> reviewData;   // 후기 데이터
    private View view;
    private NetworkTask networkTask;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_review, container, false);
        reviewList = (ListView) view.findViewById(R.id.class_review_listView);

        Button reviewButton = (Button)view.findViewById(R.id.class_review_button);
        reviewButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비로그인시
                if(!Constants.isLogined){
                    Toast.makeText(getContext(), "로그인 후 작성 가능합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //후기 작성판단
                String url = "http://106.10.35.170/ImportMyClass.php";
                String data = "email=" + AppManager.getInstance().getEmail();
                networkTask = new NetworkTask(getContext(), url, data, 7777);
                networkTask.execute();

            }
        });

        //리뷰 서버에서 가져오기
        String data = "class_index=" + ClassDetailActivity.class_index;
        String url = "http://106.10.35.170/ImportReviewList.php";
        NetworkTask networkTask = new NetworkTask(getContext(), url, data, Constants.SERVER_CLASS_REVIEW_GET);
        networkTask.execute();

        reviewList.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                reviewList.requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });

        return view;
    }


    //후기 작성 후 업데이트
    @Override
    public void onStart() {
        super.onStart();
        /*Button reviewButton = (Button)view.findViewById(R.id.class_review_button);
        reviewButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비로그인시
                if(!Constants.isLogined){
                    Toast.makeText(getContext(), "로그인 후 작성 가능합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //후기 작성판단
                String url = "http://106.10.35.170/ImportMyClass.php";
                String data = "email=" + AppManager.getInstance().getEmail();
                networkTask = new NetworkTask(getContext(), url, data, 7777);
                networkTask.execute();
            }
        });*/
        //리뷰 서버에서 가져오기
        String data = "class_index=" + ClassDetailActivity.class_index;
        String url = "http://106.10.35.170/ImportReviewList.php";
        NetworkTask networkTask = new NetworkTask(getContext(), url, data, Constants.SERVER_CLASS_REVIEW_GET);
        networkTask.execute();
    }

    // 리뷰페이지 설정하는 메소드
    public void setReviewPage() {
        // 후기 리스트 설정
        reviewList = (ListView) view.findViewById(R.id.class_review_listView);

        // 후기 데이터 설정
        reviewData = new ArrayList<>();

        /*ReviewListItem[] item = new ReviewListItem[2];
        item[0] = new ReviewListItem(R.drawable.chimi_image,"엽",1,"진짜드럽게싫다");
        item[1] = new ReviewListItem(R.drawable.cat_dog,"뭐",1,"하");
        reviewData.add(item[0]);
        reviewData.add(item[1]);*/

        // 어댑터로 후기 리스트에 아이템 뿌려주기
        adapter = new ReviewListViewAdapter(getLayoutInflater(), R.layout.class_review_listview_item, reviewData);
        reviewList.setAdapter(adapter);

    }

}
