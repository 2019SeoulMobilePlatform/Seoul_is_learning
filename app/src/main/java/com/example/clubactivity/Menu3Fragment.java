package com.example.clubactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

public class Menu3Fragment extends Fragment {
    private View view;
    static final String[] LIST_MENU = {"프랑스 자수 모임", "베이킹 모임", "기타 동호회"};
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        view = inflater.inflate(R.layout.menu3, null) ;
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_class_black_24dp),
                "Box", "Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_home_black_24dp),
                "Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_people_black_24dp),
                "Ind", "Assignment Ind Black 36dp") ;


        //어뎁터 사용 전 코드
        /*
        view = inflater.inflate(R.layout.menu3, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, LIST_MENU);

        ListView listView = (ListView) view.findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strText = (String) adapterView.getItemAtPosition(i);

            }
        });
*/
        //동호회 개설 버튼
        fab = (FloatingActionButton) view.findViewById(R.id.create_club_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddClubActivity.class);
                startActivityForResult(intent, 1); // 요청한 곳을 구분하기 위한 숫자, 의미없음
            }
        });

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {

            //mainResultTv.setText(data.getStringExtra("result"));

        }
    }
}