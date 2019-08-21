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

import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    String name;
    String email;
    String password;
    String phone_number;
    String birth;
    String residence;
    String nickname;
    String checkingPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final Spinner areaSpinner = (Spinner) findViewById(R.id.area_spinner);

        Button createAccountBtn = (Button)findViewById(R.id.btn_signup);

        final EditText inputName = (EditText)findViewById(R.id.input_name);
        final EditText inputEmail = (EditText)findViewById(R.id.input_email);
        final EditText inputPassword = (EditText)findViewById(R.id.input_password_check);
        final EditText inputPasswordCheck = (EditText)findViewById(R.id.input_password_check);
        final EditText inputNickName = (EditText)findViewById(R.id.input_nickname);
        final EditText inputPhone = (EditText)findViewById(R.id.input_phone);
        final EditText inputBirth = (EditText)findViewById(R.id.input_birth);



        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = inputName.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                checkingPassword = inputPasswordCheck.getText().toString();
                phone_number = inputPhone.getText().toString();
                birth = inputBirth.getText().toString();
                residence = areaSpinner.getSelectedItem().toString();
                nickname = inputNickName.getText().toString();


                if(name.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(inputName.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "이메일이 형식에 맞지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!password.equals(checkingPassword)){
                    Toast.makeText(SignUpActivity.this, "비밀번호를 같게 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if(phone_number.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "휴대폰 번호를 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if(nickname.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(inputName.getText().toString()).matches()){
                    Toast.makeText(SignUpActivity.this, "닉네임을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                // 강사와 유저가 같은 회원가입 Activity를 사용하기 때문에 intent 의 mode 값을 통해 나눠서 저장함
                String url =null;
                String getMode = getIntent().getStringExtra("Mode");
                if(getMode.equals("User")) {
                    url = "http://106.10.35.170/StoreUser.php";
                }else{
                    url = "http://106.10.35.170/StoreInstructor.php";
                }
                String data = getData(email, password, name, residence, birth, phone_number, nickname);
                NetworkTask networkTask = new NetworkTask(SignUpActivity.this, url, data, 2);
                networkTask.execute();

                finish();

            }
        });

    }

    private String getData(String email, String password, String name, String residence, String birth, String phone_number, String nickname ) {

        String data = "name="+ name +"&email="+ email + "&password=" + password + "&phone_number="+ phone_number
                +"&residence=" + residence + "&birth=" + birth + "&nickname=" + nickname;

        return data;
    }
}
