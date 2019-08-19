package com.example.clubactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.clubactivity.Login.LoginActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("state","launch");
        startActivity(intent);
        finish();
    }
}
