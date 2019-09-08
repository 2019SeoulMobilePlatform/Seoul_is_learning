package com.example.clubactivity.Class;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clubactivity.Constants;
import com.example.clubactivity.R;

public class ClassReservation extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_reservation_pop);

        ImageView imageView = (ImageView)findViewById(R.id.res_class_image);
        imageView.setImageResource(getIntent().getIntExtra("image",2));

        TextView title = (TextView)findViewById(R.id.res_class_title);
        title.setText(getIntent().getStringExtra("param"));
        TextView desc = (TextView)findViewById(R.id.res_class_detail);
        desc.setText(ClassDetailActivity.desc);
        TextView location = (TextView)findViewById(R.id.res_class_place);
        location.setText(ClassDetailActivity.location);
        TextView date = (TextView)findViewById(R.id.res_class_date);
        date.setText(ClassDetailActivity.date);
        TextView time = (TextView)findViewById(R.id.res_class_time);
        //time.setText(ClassDetailActivity.date);
        TextView price = (TextView)findViewById(R.id.res_class_price);
        price.setText("50,000원");
    }


    //클래스 예약 뒤로가기 버튼 클릭
    public void ClassReservationBackButtonClicked(View view){
        finish();
    }

    //클래스 예약 버튼 클릭
    public void ClassReservationFinalButtonClicked(View view){
        if(!Constants.isLogined){
            Toast.makeText(getApplicationContext(), "클래스 예약은 로그인 후 진행 가능합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "클래스 예약이 완료되었습니다!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
