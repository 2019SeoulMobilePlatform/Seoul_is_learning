package com.example.clubactivity.Class;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

public class ClassReservation extends Activity {

    public int class_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_reservation_pop);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView imageView = (ImageView)findViewById(R.id.res_class_image);
        imageView.setImageBitmap(bitmap);

        class_index = getIntent().getIntExtra("class_index",0);


        TextView title = (TextView)findViewById(R.id.res_class_title);
        title.setText(getIntent().getStringExtra("param"));
        TextView desc = (TextView)findViewById(R.id.res_class_detail);
        desc.setText(ClassDetailActivity.desc);
        TextView location = (TextView)findViewById(R.id.res_class_place);
        location.setText(ClassDetailActivity.location);
        TextView date = (TextView)findViewById(R.id.res_class_date);
        date.setText(ClassDetailActivity.date);
        TextView people = (TextView)findViewById(R.id.res_class_time);
        people.setText(ClassDetailActivity.number+"명");

        TextView price = (TextView)findViewById(R.id.res_class_price);
        price.setText(ClassDetailActivity.price+"원");
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

        String url = "http://106.10.35.170/ReservateClass.php";
        String data = getData(class_index ,AppManager.getInstance().getEmail());

        NetworkTask networkTask = new NetworkTask(ClassReservation.this, url, data, Constants.SERVER_CLASS_RESERVATION);
        networkTask.execute();


        finish();
    }


    public String getData(int class_index, String email){

        String data = "class_index=" + class_index + "&email=" + email;

        return data;
    }

}
