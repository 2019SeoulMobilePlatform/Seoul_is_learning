package com.example.clubactivity.Class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        //Bundle bundle = new Bundle();

       /* TextView textView2 = view.findViewById(R.id.class_sogae_intent);
        textView2.setText(getArguments().getString("desc"));
        TextView textView3 = view.findViewById(R.id.class_member_intent);
        textView3.setText(getArguments().getString("people"));
        TextView textView4 = view.findViewById(R.id.class_location_intent);
        textView4.setText(getArguments().getString("location"));
        TextView textView5 = view.findViewById(R.id.class_time_intent);
        textView5.setText(getArguments().getString("date"));
        TextView textView6 = view.findViewById(R.id.class_people_number_intent);
        textView6.setText(getArguments().getString("number"));*/


        return view;
    }
}
