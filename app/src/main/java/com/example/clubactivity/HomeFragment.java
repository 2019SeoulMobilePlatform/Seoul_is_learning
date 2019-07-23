package com.example.clubactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.example.sliderviewlibrary.SliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private View view;
   /* ArrayList<Integer> images = new ArrayList<>();
    SliderView sliderView;
    int DELAY_MS=1000,PERIOD_MS=1000; //시간 정하기
    ArrayList<String> urls = new ArrayList<>(); //주소로 가져오기*/

   SliderView sliderView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);

        sliderView = view.findViewById(R.id.imageSlider);

        final SliderAdapter adapter = new SliderAdapter(getActivity());
        adapter.setCount(4);

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


       /* sliderView=view.findViewById(R.id.sliderView);
        //SliderAdapter sliderAdapter = new SliderAdapter(getApplicationContext(),viewPager);
        images.add(R.drawable.slide1);
        images.add(R.drawable.slide2);
        images.add(R.drawable.slide3);

        //urls.add("https://cfile1.onoffmix.com/images/event/188040");
        //urls.add("https://cfile1.onoffmix.com/images/event/188040");
        //urls.add("https://cfile1.onoffmix.com/images/event/188040");

        sliderView.setImages(images);
        //sliderView.setUrls(urls);

        TimerTask task = sliderView.getTimerTask();
        Timer timer = new Timer();
        timer.schedule(task,DELAY_MS,PERIOD_MS);*/

        return view;
    }

}
