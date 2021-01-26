package com.example.clubactivity.MyPage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

        TextView logout = findViewById(R.id.logout);
        SpannableString content = new SpannableString("로그아웃");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        logout.setText(content);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
                AlertDialog.Builder alBuilder = new AlertDialog.Builder(EnterEditInfoActivity.this);
                alBuilder.setMessage("로그아웃 하시겠습니까?");

                // "예" 버튼을 누르면 실행되는 리스너
                alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Constants.isLogined = false;
                        finish(); // 현재 액티비티를 종료한다.
                    }
                });
                // "아니오" 버튼을 누르면 실행되는 리스너
                alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return; // 아무런 작업도 하지 않고 돌아간다
                    }
                });

                alBuilder.setTitle("서울은 배우는중");
                alBuilder.setIcon(R.drawable.seoul_is_learning); //아이콘 설정
                alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
            }
        });

    }

}
