package com.example.clubactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.Locale;

public class ClubFragment extends Fragment {
    private View view;
    FloatingActionButton fab;
    ListView myClub_Listview;
    ListView whole_ClubListView;
    ChatViewAdapter myClub_adapter;
    ChatViewAdapter searchClub_adapter;
    ChatViewAdapter wholeClub_adapter;
    private EditText searchBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.club, null);

        TabHost tabHost1 = (TabHost) view.findViewById(R.id.tapHost_chatlist) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1_chatlist) ;
        ts1.setIndicator("전체 동호회") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2_chatlist) ;
        ts2.setIndicator("나의 동호회") ;
        tabHost1.addTab(ts2) ;

        // Adapter 생성
        myClub_adapter = new ChatViewAdapter() ;
        searchClub_adapter = new ChatViewAdapter();
        wholeClub_adapter = new ChatViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        myClub_Listview = (ListView) view.findViewById(R.id.myclub_listview);
        myClub_Listview.setAdapter(myClub_adapter);
        whole_ClubListView = (ListView) view.findViewById(R.id.wholeclub_listview);
        whole_ClubListView.setAdapter(wholeClub_adapter);

        // 임시 아이템 추가.
        wholeClub_adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.cat_dog),
                "일러스트 동호회 모집", "일러스트에 관심 있으신 분들 환영합니다~~") ;
        wholeClub_adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_home_black_24dp),
                "Circle", "Account Circle Black 36dp") ;
        wholeClub_adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_people_black_24dp),
                "Ind", "Assignment Ind Black 36dp");
        myClub_adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_people_black_24dp),
                "Ind", "Assignment Ind Black 36dp") ;
        myClub_adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_home_black_24dp),
                "Circle", "Account Circle Black 36dp") ;


        //아이템 클릭시 채팅방 들어가기
        myClub_Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity() , ((ChatViewItem)myClub_adapter.getItem(i)).getTitle(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                startActivityForResult(intent, 1001);
            }
        });

        //동호회 개설 버튼
        fab = (FloatingActionButton) view.findViewById(R.id.create_club_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddClubActivity.class);
                startActivityForResult(intent, 1); // 요청한 곳을 구분하기 위한 숫자, 의미없음
            }
        });


        searchBar = (EditText) view.findViewById(R.id.search);
        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable edit) {
                String text = searchBar.getText().toString().toLowerCase(Locale.getDefault());
                //내가 소속되지 않은 동호회 리스트 검색
                whole_ClubListView.setAdapter(searchClub_adapter);
                searchClub_adapter.removeAllItem();

                if (searchBar.length() == 0) {
                    whole_ClubListView.setAdapter(wholeClub_adapter);
                    // 문자 입력이 없을때는 모든 데이터를 보여준다.
                    //for (ChatViewItem mItem : wholeClub_adapter.chatViewItemList) {
                    //    searchClub_adapter.addItem(mItem);
                    //}
                }
                // 문자 입력을 할때..
                else {
                    // 리스트의 모든 데이터를 검색한다.
                    for (int i = 0; i < wholeClub_adapter.chatViewItemList.size(); i++) {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (((ChatViewItem)wholeClub_adapter.getItem(i)).getTitle().toLowerCase().contains(text) ||
                                ((ChatViewItem)wholeClub_adapter.getItem(i)).getDesc().toLowerCase().contains(text)) {
                            // 검색된 데이터를 리스트에 추가한다.
                            searchClub_adapter.addItem((ChatViewItem)wholeClub_adapter.getItem(i));
                        }
                    }
                }
                // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                searchClub_adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {

            }
        });

        //검색 텍스트 모두 지우기
        ImageButton remove_allText_btn = (ImageButton) view.findViewById(R.id.remove_allText_button);
        remove_allText_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setText("");
            }
        });
        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
            Toast.makeText(getActivity() , "액티비티종료",Toast.LENGTH_LONG).show();

            myClub_adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_class_black_24dp),
                    (String)data.getExtras().get("clubName"), (String)data.getExtras().get("clubDescription")) ;

            //갱신
            myClub_adapter.notifyDataSetChanged();
        }
    }


}