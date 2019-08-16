package com.example.clubactivity.Login;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clubactivity.Constants;
import com.example.clubactivity.MainActivity;
import com.example.clubactivity.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class LoginFragment extends Fragment {
    private View view;
    String email;
    String password;
    EditText _email;
    EditText _password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login, container, false);

        _email = (EditText) (view.findViewById(R.id.input_email));
        email = _email.getText().toString();
        _password = (EditText) (view.findViewById(R.id.input_password));
        password = _password.getText().toString();

        view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        //회원가입
        view.findViewById(R.id.link_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivityForResult(intent, Constants.REQUEST_SIGN_UP);
            }
        });

        //비밀번호찾기
        view.findViewById(R.id.link_find_password).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindPasswordAcitvity.class);
                startActivityForResult(intent, Constants.REQUEST_FIND_PASSWORD);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        view.findViewById(R.id.login_instructor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();

                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .replace(R.id.login_frame,  LoginActivity.loginInstructorFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });



    }


    public void login() {

        email = _email.getText().toString();
        password = _password.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getActivity(),"이메일, 비밀번호를 입력해 주세요",Toast.LENGTH_LONG).show();
            return;
        }

        //서버에 로그인 정보 보내기
        //sendData( email, password ) ;


        //서버와 로그인 정보 비교


        //로그인 성공
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    private void sendData(String userId, String userPassword ) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://106.10.35.170/CheckSignIn.php");
        ArrayList<NameValuePair> nameValues =
                new ArrayList<NameValuePair>();

        try {
            //Post방식으로 넘길 값들을 각각 지정을 해주어야 한다.
            nameValues.add(new BasicNameValuePair(
                    "email", URLDecoder.decode(userId, "UTF-8")));
            nameValues.add(new BasicNameValuePair(
                    "password", URLDecoder.decode(userPassword, "UTF-8")));

            //HttpPost에 넘길 값을들 Set해주기
            post.setEntity(
                    new UrlEncodedFormEntity(
                            nameValues, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Log.e("Insert Log", ex.toString());
        }

        try {
            //설정한 URL을 실행시키기
            HttpResponse response = client.execute(post);
            //통신 값을 받은 Log 생성. (200이 나오는지 확인할 것~) 200이 나오면 통신이 잘 되었다는 뜻!
            Log.i("Insert Log", "response.getStatusCode:" + response.getStatusLine().getStatusCode());

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
