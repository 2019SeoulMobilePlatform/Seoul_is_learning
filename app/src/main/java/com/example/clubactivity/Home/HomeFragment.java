package com.example.clubactivity.Home;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.clubactivity.Class.ClassFragment;
import com.example.clubactivity.Class.ClassList;
import com.example.clubactivity.R;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements DatePickerListener {

    private View view;
    SliderView sliderView;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("MM/dd"); //날짜형식
    TextView mTextView;

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    ClassList classList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);
        classList = new ClassList();

        //데이트영역
        // find the picker
        HorizontalPicker picker = (HorizontalPicker) view.findViewById(R.id.datePicker);
        // initialize it and attach a listener
        picker
                .setListener(this)
                .init();
        picker.setBackgroundColor(Color.WHITE);
        picker.setDate(new DateTime());


        //슬라이드영역
        sliderView = view.findViewById(R.id.imageSlider);
        final SliderAdapter sliderAdapter = new SliderAdapter(getActivity());
        sliderAdapter.setCount(3); //카드뷰 개수 정해주기

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        //sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(R.color.colorPrimary);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        //sliderView.setScrollTimeInSec(50);
        //sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });


        //전체보기
        ClassFragment.classmenuTitle = "인기 클래스";
        Button allViewButton = (Button)view.findViewById(R.id.class_all_button);

        allViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassListCall();
            }
        });


        //밑에 뷰페이저 영역
        models = new ArrayList<>();
        models.add(new Model(R.drawable.cooking_class, "하루에 한가지 요리!", "요리를 간단하게! 단 하루에 하나씩만 배웁시다.","","","","",""));
        models.add(new Model(R.drawable.gaebal_class, "웹코딩을 배우자!", "나만의 사이트를 만들 수 있는 클래스","광진구","웹코딩을 쉽게 배우고 싶은 사람들","서울시 광진구 자양3동 스타시티 건물 7층","2019년 12월 매주 월, 수","20명"));
        models.add(new Model(R.drawable.draw_class, "그림 어렵지 않습니다.", "당신의 머리속 그림을 그려봅시다.","","","","",""));

        adapter = new Adapter(models, getActivity());


        int dpValue = 55;
        float displaySize = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * displaySize);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(margin, 0, margin, 0); // 미리보기정도
        //viewPager.setPageMargin(margin/2); //서로 간격


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDateSelected(DateTime dateSelected) {
        Log.i("HorizontalPicker", "Selected date is " + dateSelected.toString());
    }

    public void ClassListCall()
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_frame, classList).commit();
    }




}
