package com.example.clubactivity.Login;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.R;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner areaSpinner = (Spinner) findViewById(R.id.area_spinner);

        Button createAccountBtn = (Button)findViewById(R.id.btn_signup);

        final EditText inputName = (EditText)findViewById(R.id.input_password);
        EditText inputEmail = (EditText)findViewById(R.id.input_email);
        EditText inputPassword = (EditText)findViewById(R.id.input_password_check);
        EditText inputPasswordCheck = (EditText)findViewById(R.id.input_password_check);
        EditText inputNickName = (EditText)findViewById(R.id.input_nickname);
        EditText inputPhone = (EditText)findViewById(R.id.input_phone);
        EditText inputBirth = (EditText)findViewById(R.id.input_birth);



        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }


                finish();

            }
        });



    }
}
