package com.example.clubactivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditMyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_info);

        Button editButton = (Button) findViewById(R.id.edit_info_btn);
        final EditText password = (EditText) findViewById(R.id.edit_password);
        final EditText passwordCheck = (EditText) findViewById(R.id.edit_password_check);

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals(passwordCheck.getText().toString())){
                    Toast.makeText(EditMyInfoActivity.this, "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG);
                    return;
                }

                Intent intent = new Intent();


                //intent.putExtra("password", .getText().toString());
                //intent.putExtra("nickname", .getText().toString());

                setResult(RESULT_OK, intent);
                EditMyInfoActivity.this.finish();
            }
        });

    }
}
