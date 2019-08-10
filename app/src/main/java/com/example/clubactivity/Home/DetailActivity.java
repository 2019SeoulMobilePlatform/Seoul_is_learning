package com.example.clubactivity.Home;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clubactivity.R;

public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("param"));
    }
}
