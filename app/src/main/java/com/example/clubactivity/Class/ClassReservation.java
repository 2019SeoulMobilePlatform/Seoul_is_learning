package com.example.clubactivity.Class;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.clubactivity.R;

public class ClassReservation extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_reservation_pop);


    }


    //클래스 예약 뒤로가기 버튼 클릭
    public void ClassReservationBackButtonClicked(View view){
        finish();
    }

    //클래스 예약 버튼 클릭
    public void ClassReservationFinalButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "클래스 예약이 완료되었습니다!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
