package com.example.clubactivity;

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
import androidx.viewpager.widget.ViewPager;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    //Integer[] colors = null;
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
        sliderAdapter.setCount(5); //카드뷰 개수 정해주기

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });




        //밑에 뷰페이저 영역
        models = new ArrayList<>();
        models.add(new Model(R.drawable.seoul_is_learning, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new Model(R.drawable.cat_dog, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new Model(R.drawable.seoul_is_learning, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new Model(R.drawable.seoul_is_learning, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new Adapter(models, getActivity());

        int dpValue = 55;
        float displaySize = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * displaySize);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(margin, 0, margin, 0); // 미리보기정도
        //viewPager.setPageMargin(margin/2); //서로 간격

     /*   Integer[] colors_temp = {
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.black)
        };

        colors = colors_temp;*/

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                /*if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }*/
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

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
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
