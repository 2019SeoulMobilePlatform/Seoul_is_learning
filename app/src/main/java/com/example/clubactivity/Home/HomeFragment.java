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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);

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
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

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
                ClassList classList = new ClassList();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.main_frame, classList).commit();
            }
        });


        //밑에 뷰페이저 영역
        models = new ArrayList<>();
        models.add(new Model(R.drawable.seoul_is_learning, "냥냥", "으아아아앙아앙랑 ㄹ알알아랑라말ㅇ라알아라아아아앙아ㅏ아아"));
        models.add(new Model(R.drawable.cat_dog, "냠냠", "으아아아앙아앙랑 ㄹ알알아랑라말ㅇ라알아라아아아앙아ㅏ아아"));
        models.add(new Model(R.drawable.seoul_is_learning, "헤헿", "으아아아앙아앙랑 ㄹ알알아랑라말ㅇ라알아라아아아앙아ㅏ아아"));
        models.add(new Model(R.drawable.seoul_is_learning, "너무졸린데", "으아아아앙아앙랑 ㄹ알알아랑라말ㅇ라알아라아아아앙아ㅏ아아"));

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





}
