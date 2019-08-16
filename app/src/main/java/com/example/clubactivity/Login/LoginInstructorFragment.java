package com.example.clubactivity.Login;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.clubactivity.InstructorMainActivity;
import com.example.clubactivity.MainActivity;
import com.example.clubactivity.R;

public class LoginInstructorFragment extends Fragment {
    private View view;
    String email;
    String password;
    EditText _email;
    EditText _password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_instructor, container, false);

        _email = (EditText) (view.findViewById(R.id.input_email_instructor));
        email = _email.getText().toString();
        _password = (EditText) (view.findViewById(R.id.input_password_instructor));
        password = _password.getText().toString();

        view.findViewById(R.id.link_signup_instructor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivityForResult(intent, Constants.REQUEST_SIGN_UP);
            }
        });

        view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        view.findViewById(R.id.login_memeber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();

                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left, R.anim.exit_right_to_left)
                        .replace(R.id.login_frame, LoginActivity.loginFragment)
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
        Intent intent = new Intent(getActivity(), InstructorMainActivity.class);
        startActivity(intent);
    }
}

