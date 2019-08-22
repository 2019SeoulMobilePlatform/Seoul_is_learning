package com.example.clubactivity.Class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clubactivity.R;

import java.util.Locale;


public class ClassFragment extends Fragment implements View.OnClickListener{

    private View view;
    public static String classmenuTitle;
    private ClassList classList = new ClassList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.class_, container, false);


       /*
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
        */

        FrameLayout eunpyung_button = view.findViewById(R.id.fl_mainfragment_eunpyung);
        FrameLayout gangbuk_button = view.findViewById(R.id.fl_mainfragment_gangbuk);
        FrameLayout dobong_button = view.findViewById(R.id.fl_mainfragment_dobong);
        FrameLayout nowon_button = view.findViewById(R.id.fl_mainfragment_nowon);
        FrameLayout seodaemun_button = view.findViewById(R.id.fl_mainfragment_seodaemun);
        FrameLayout jongro_button = view.findViewById(R.id.fl_mainfragment_jongro);
        FrameLayout seongbuk_button = view.findViewById(R.id.fl_mainfragment_seongbuk);
        FrameLayout mapo_button = view.findViewById(R.id.fl_mainfragment_mapo);
        FrameLayout junggu_button = view.findViewById(R.id.fl_mainfragment_junggu);
        FrameLayout dongdaemun_button = view.findViewById(R.id.fl_mainfragment_dongdaemun);
        FrameLayout yongsan_button = view.findViewById(R.id.fl_mainfragment_yongsan);
        FrameLayout seongdong_button = view.findViewById(R.id.fl_mainfragment_seongdong);
        FrameLayout jungrang_button = view.findViewById(R.id.fl_mainfragment_jungnang);
        FrameLayout gwangjin_button = view.findViewById(R.id.fl_mainfragment_gwangjin);

        FrameLayout gangseo_button = view.findViewById(R.id.fl_mainfragment_gangseo);
        FrameLayout yangcheon_button = view.findViewById(R.id.fl_mainfragment_yangcheon);
        FrameLayout guro_button = view.findViewById(R.id.fl_mainfragment_guro);
        FrameLayout yeongdeungpo_button = view.findViewById(R.id.fl_mainfragment_yeongdeungpo);
        FrameLayout dongjak_button = view.findViewById(R.id.fl_mainfragment_dongjak);
        FrameLayout gwanak_button = view.findViewById(R.id.fl_mainfragment_gwanak);
        FrameLayout geumcheon_button = view.findViewById(R.id.fl_mainfragment_geumcheon);
        FrameLayout seocho_button = view.findViewById(R.id.fl_mainfragment_seocho);
        FrameLayout gangnam_button = view.findViewById(R.id.fl_mainfragment_gangnam);
        FrameLayout gangdong_button = view.findViewById(R.id.fl_mainfragment_gangdong);
        FrameLayout songpa_button = view.findViewById(R.id.fl_mainfragment_songpa);


        eunpyung_button.setOnClickListener(this);
        gangbuk_button.setOnClickListener(this);
        dobong_button.setOnClickListener(this);
        nowon_button.setOnClickListener(this);
        seodaemun_button.setOnClickListener(this);
        jongro_button.setOnClickListener(this);
        seongbuk_button.setOnClickListener(this);
        mapo_button.setOnClickListener(this);
        junggu_button.setOnClickListener(this);
        dongdaemun_button.setOnClickListener(this);
        yongsan_button.setOnClickListener(this);
        seongdong_button.setOnClickListener(this);
        jungrang_button.setOnClickListener(this);
        gwangjin_button.setOnClickListener(this);

        gangseo_button.setOnClickListener(this);
        yangcheon_button.setOnClickListener(this);
        guro_button.setOnClickListener(this);
        yeongdeungpo_button.setOnClickListener(this);
        dongjak_button.setOnClickListener(this);
        gwanak_button.setOnClickListener(this);
        geumcheon_button.setOnClickListener(this);
        seocho_button.setOnClickListener(this);
        gangnam_button.setOnClickListener(this);
        gangdong_button.setOnClickListener(this);
        songpa_button.setOnClickListener(this);




       //RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.class_list);



        //검색 텍스트 검색
        ImageButton searchButton = (ImageButton) view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText searchBar = (EditText) view.findViewById(R.id.class_search);
                String searchText = searchBar.getText().toString().toLowerCase(Locale.getDefault());

                if(searchText.length() == 0) {
                    Toast.makeText(getContext(), "검색어를 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                else {
                    classmenuTitle = searchText;
                    ShowClassList();
                }

            }
        });

        return view;
    }


    @Override
    public void onClick(View v){
        int id = v.getId();

        switch(id){
            case R.id.fl_mainfragment_eunpyung :
                classmenuTitle = "은평구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_gangbuk :
                classmenuTitle = "강북구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_dobong :
                classmenuTitle = "도봉구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_nowon :
                classmenuTitle = "노원구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_seodaemun :
                classmenuTitle = "서대문구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_jongro :
                classmenuTitle = "종로구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_seongbuk :
                classmenuTitle = "성북구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_mapo :
                classmenuTitle = "마포구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_junggu :
                classmenuTitle = "중구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_dongdaemun :
                classmenuTitle = "동대문구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_yongsan :
                classmenuTitle = "용산구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_seongdong :
                classmenuTitle = "성동구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_jungnang :
                classmenuTitle = "중랑구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_gwangjin :
                classmenuTitle = "광진구";
                ShowClassList();
                break;

            case R.id.fl_mainfragment_gangseo  :
                classmenuTitle = "강서구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_yangcheon  :
                classmenuTitle = "양천구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_guro  :
                classmenuTitle = "구로구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_yeongdeungpo  :
                classmenuTitle = "영등포구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_dongjak  :
                classmenuTitle = "동작구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_gwanak  :
                classmenuTitle = "관악구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_geumcheon  :
                classmenuTitle = "금천구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_seocho  :
                classmenuTitle = "서초구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_gangnam  :
                classmenuTitle = "강남구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_gangdong  :
                classmenuTitle = "강동구";
                ShowClassList();
                break;
            case R.id.fl_mainfragment_songpa  :
                classmenuTitle = "송파구";

                //((MainActivity)getActivity()).replaceFragment(ClassFragment.newInstance());


                ShowClassList();

                break;
        }
    }

    public void ShowClassList(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, classList).commit();
        fragmentTransaction.addToBackStack(null);
        if(ClassFragment.this.isAdded())
        {
            return; //or return false/true, based on where you are calling from
        }
    }

    /*public static ClassList newInstance() {
        return new ClassList();
    }*/


}
