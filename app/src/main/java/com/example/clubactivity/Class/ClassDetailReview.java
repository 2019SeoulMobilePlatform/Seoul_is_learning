package com.example.clubactivity.Class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clubactivity.R;

import java.util.ArrayList;

public class ClassDetailReview extends Fragment {

    private ReviewListViewAdapter adapter;  //후기 리스트뷰 어댑터
    private ListView reviewList;         // 후기 리스트
    private ArrayList<ReviewListItem> reviewData;   // 후기 데이터
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_review, container, false);


        Button reviewButton = (Button)view.findViewById(R.id.class_review_button);
        reviewButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"클래스를 들은 회원만 작성 가능합니다", Toast.LENGTH_LONG).show();
            }
        });

        setReviewPage();

        return view;
    }


    // 리뷰페이지 설정하는 메소드
    public void setReviewPage() {
        // 후기 리스트 설정
        reviewList = (ListView) view.findViewById(R.id.class_review_listView);

        // 후기 데이터 설정
        reviewData = new ArrayList<>();

        ReviewListItem[] item = new ReviewListItem[2];
        item[0] = new ReviewListItem(R.drawable.chimi_image,"엽",1,"진짜드럽게싫다");
        item[1] = new ReviewListItem(R.drawable.cat_dog,"뭐",1,"하");
        reviewData.add(item[0]);
        reviewData.add(item[1]);


        // 어댑터로 후기 리스트에 아이템 뿌려주기
        adapter = new ReviewListViewAdapter(getLayoutInflater(), R.layout.class_review_listview_item, reviewData);
        reviewList.setAdapter(adapter);

    }

}
