package com.example.clubactivity.Login;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.GmailSender;
import com.example.clubactivity.R;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class FindPasswordAcitvity extends AppCompatActivity {

    EditText emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);
        emailText = findViewById(R.id.input_email);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());


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
                //TODO : 이메일 서버에 있는지 체크
                try {
                    GmailSender gMailSender = new GmailSender("sj980619@gmail.com", "rlatnwjd");
                    String password = gMailSender.createRandomString();
                    //GMailSender.sendMail(제목, 본문내용, 받는사람);
                    gMailSender.sendMail("서울은 배우는 중 비밀번호 변경", "서울은 배우는 중 발신 \n\n 비밀번호가 ["+gMailSender.createRandomString()+"]로 변경되었습니다.", emailText.getText().toString());
                    Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
                } catch (SendFailedException e) {
                    Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (MessagingException e) {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
