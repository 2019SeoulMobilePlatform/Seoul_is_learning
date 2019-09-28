package com.example.clubactivity.Class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

public class ClassList extends Fragment {
    final int ITEM_SIZE = 5;
    private View view;
    NetworkTask networkTask;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.class_list, container, false);

      /*  String data = "local=" + ClassFragment.classmenuTitle;
        String url = "http://106.10.35.170/ImportClassList.php";
        networkTask = new NetworkTask(getContext(), url, data, 5);
        networkTask.execute();
        networkTask.ServerClassList();*/

        /*RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.class_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);*/


     /*   List<Item> items = networkTask.ServerClassList();
        Item[] item = new Item[ITEM_SIZE];
        item[0] = new Item(R.drawable.class1, 4,"창업 클래스","창업설명","송파구","창업하쉴?","송파구11번가","2020년","1명");
        item[1] = new Item(R.drawable.class2,3, "개발자 클래스","","","","","","");
        item[2] = new Item(R.drawable.class3, 2,"인프라 클래스","","","","","","");
        item[3] = new Item(R.drawable.class4,1, "취업 크래스","","","","","","");
        item[4] = new Item(R.drawable.class1, 0,"헤헿","","","","","","");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getContext(), items, R.layout.class_list));*/


        //위에 제목 지정
        TextView classListTitle = view.findViewById(R.id.class_list_area_or_title);
        classListTitle.setText(ClassFragment.classmenuTitle);

        //뒤로가기
        ImageView classBack = view.findViewById(R.id.class_list_back_button);
        classBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(ClassList.this).commit();
                fragmentManager.popBackStack();
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String data=null;
        String url=null;

        if(ClassFragment.classmenuTitle == "추천 클래스") {
            data = "email=" + AppManager.getInstance().getEmail();
            url = "http://106.10.35.170/ImportRecommandClassList.php";
        }
        else if(ClassFragment.classmenuTitle == "원데이 클래스" ){
            data = "email=" + AppManager.getInstance().getEmail();
            url = "http://106.10.35.170/ImportOneDayClassList.php";
        }
        else if(ClassFragment.classmenuTitle == "개발 클래스"){
            data = "email=" + AppManager.getInstance().getEmail();
            url = "http://106.10.35.170/ImportDevelopmentClassList.php";
        }
        else if(ClassFragment.updateFlag==2){
            data = "local=" + ClassFragment.classmenuTitle + "&email=" + AppManager.getInstance().getEmail();
            url = "http://106.10.35.170/ImportClassList.php";
        }
        else if(ClassFragment.updateFlag==1){
            data = "word=" + ClassFragment.classmenuTitle + "&email=" + AppManager.getInstance().getEmail();
            url = "http://106.10.35.170/SearchClassList.php";
        }
        else
            return;
        NetworkTask networkTask = new NetworkTask(getContext(), url, data, Constants.SERVER_CLASS_LIST_GET);
        networkTask.execute();
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //액티비티 꺼졌을때..!
    }*/


}

