package com.example.clubactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HomeFragment extends Fragment {

    private View view;
    SliderView sliderView;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("MM/dd"); //날짜형식
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);

        sliderView = view.findViewById(R.id.imageSlider);
        mTextView = view.findViewById(R.id.monday_day);
        mTextView.setText(getTime()); // 날짜가져오기
        mTextView = view.findViewById(R.id.tuesday_day);
        mTextView.setText(getTime()+1); // 날짜가져오기

        final SliderAdapter adapter = new SliderAdapter(getActivity());
        adapter.setCount(5); //카드뷰 개수 정해주기

        sliderView.setSliderAdapter(adapter);

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


}
