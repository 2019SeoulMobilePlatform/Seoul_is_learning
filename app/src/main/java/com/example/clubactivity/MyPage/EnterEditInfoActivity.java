package com.example.clubactivity.MyPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.Constants;
import com.example.clubactivity.R;

public class EnterEditInfoActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_edit_info);
        Button button = findViewById(R.id.input_password_btn);
        final EditText editText = findViewById(R.id.input_password_editText);

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!preferences.getString("password", "").equals(editText.getText().toString())){
                    Toast.makeText(EnterEditInfoActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(EnterEditInfoActivity.this, EditMyInfoActivity.class);
                intent.putExtra("isInstructor", getIntent().getBooleanExtra("isInstructor", false) );
                startActivityForResult(intent, Constants.REQUEST_EDIT_INFO);
                finish();
            }
        });

    }

}
