package com.example.clubactivity.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.R;

public class FindPasswordAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);

        Button backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindPasswordAcitvity.this.finish();
            }
        });

        Button findPasswordButton = (Button) findViewById(R.id.btn_find_password);
        findPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이메일 서버에 있으면 메일로 비밀번호 보내기
            }
        });
    }
}
