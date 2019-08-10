package com.example.clubactivity.Class;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubactivity.R;

import java.util.ArrayList;
import java.util.List;

public class ClassList extends Activity {
    final int ITEM_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list);

        //클래스 지역구 바꿔준다
        EditText editText = findViewById(R.id.class_searching);
        editText.setHint(getIntent().getStringExtra("param"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.class_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Item> items = new ArrayList<>();
        Item[] item = new Item[ITEM_SIZE];
        item[0] = new Item(R.drawable.class1, "창업 클래스");
        item[1] = new Item(R.drawable.class2, "개발자 클래스");
        item[2] = new Item(R.drawable.class3, "인프라 클래스");
        item[3] = new Item(R.drawable.class4, "취업 크래스");
        item[4] = new Item(R.drawable.class1, "헤헿");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.class_list));
    }
}
