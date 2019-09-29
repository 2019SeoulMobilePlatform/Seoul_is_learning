package com.example.clubactivity.Class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clubactivity.R;

public class ClassDetailIntroduction extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_introduction, container, false);

        TextView textView2 = view.findViewById(R.id.class_sogae_intent);//소개
        textView2.setText(ClassDetailActivity.desc);
        TextView textView3 = view.findViewById(R.id.class_member_intent);//대상
        textView3.setText(ClassDetailActivity.people);
        TextView textView4 = view.findViewById(R.id.class_location_intent);//장소
        textView4.setText(ClassDetailActivity.location);
        TextView textView5 = view.findViewById(R.id.class_time_intent);//시간
        textView5.setText(ClassDetailActivity.date);
        TextView textView7 = view.findViewById(R.id.class_people_number_intent_now); //현재 인원
        textView7.setText("현재 인원 : "+ClassDetailActivity.number_now+"명");
        TextView textView6 = view.findViewById(R.id.class_people_number_intent);//인원
        textView6.setText("모집 인원 : "+ClassDetailActivity.number +"명");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
            TextView textView7 = view.findViewById(R.id.class_people_number_intent_now); //현재 인원
            textView7.setText("현재 인원 : " + ClassDetailActivity.number_now + "명");
    }
}
