package com.example.clubactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class ClassFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.class_, container, false);

        ImageButton gwangjin_button = (ImageButton)view.findViewById(R.id.imageView3);

        gwangjin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassList.class);
                startActivity(intent);
            }
        });


        FrameLayout eunpyung_button = view.findViewById(R.id.fl_mainfragment_eunpyung);

        eunpyung_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassList.class);
                startActivity(intent);
            }
        });


        return view;
    }



}
