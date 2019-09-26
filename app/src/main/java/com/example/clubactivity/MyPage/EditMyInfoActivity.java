package com.example.clubactivity.MyPage;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.clubactivity.Club.AddClubActivity;
import com.example.clubactivity.Constants;
import com.example.clubactivity.ImageProcessing;
import com.example.clubactivity.Network.ImageConverter;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditMyInfoActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList<String> arrayList;
    Bitmap _userImage = null;
    EditText _nickname;
    EditText _phonenumber;
    EditText _email;
    EditText _password;
    EditText _password_check;
    Spinner areaSpinner;

    CircleImageView profileImage;

    String nickname;
    String phonenumber;
    String password;
    String password_check;
    String residence;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_info);
        setArrayList();

        Button editButton = (Button) findViewById(R.id.edit_info_btn);
        _nickname = (EditText) findViewById(R.id.edit_nickname);
        _phonenumber = (EditText)findViewById(R.id.edit_phone);
        _email = (EditText)findViewById(R.id.edit_email);
        _password = (EditText)findViewById(R.id.edit_password);
        _password_check = (EditText)findViewById(R.id.edit_password_check);
        areaSpinner = (Spinner)findViewById(R.id.area_spinner);
        profileImage = (CircleImageView)findViewById(R.id.user_image) ;


        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        editor = preferences.edit();

        //기본 에디트텍스트 지정
        _nickname.setText(preferences.getString("nickname", ""));
        _phonenumber.setText(preferences.getString("phone_number", ""));
        _email.setText(preferences.getString("email",""));
        _password.setText(preferences.getString("password",""));
        _password_check.setText(preferences.getString("password", ""));

        //기본스피너 지정
        residence = preferences.getString("residence","");
        areaSpinner.setSelection(getPosition(residence));



        if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null) {
            Bitmap image = ImageConverter.getImageToBitmap(preferences.getString("profileImage", ""));
            profileImage.setImageBitmap(image);
            _userImage = image;
        }
        else{
            profileImage.setImageResource(R.drawable.ic_account_circle_white_60dp);
        }

        final EditText passwordCheck = (EditText) findViewById(R.id.edit_password_check);

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nickname = _nickname.getText().toString();
                phonenumber = _phonenumber.getText().toString();
                password = _password.getText().toString();
                email = _email.getText().toString();
                residence = areaSpinner.getSelectedItem().toString();
                //if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
                //프로필이미지가 기본이미지이냐
//                if(profileImage.getDrawable() != getResources().getDrawable(R.drawable.ic_account_circle_white_60dp))
//                    _userImage = ((BitmapDrawable)profileImage.getDrawable()).getBitmap();
//                else
//                    _userImage = null;

                if(!_password.getText().toString().equals(passwordCheck.getText().toString())){
                    Toast.makeText(EditMyInfoActivity.this, "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    Log.d("","비밀번호 불일치");
                    return;
                }

                if(!Pattern.matches("^(?=.*\\d)(?=.*[a-zA-Z]).{8,12}$", _password.getText().toString())){
                    Toast.makeText(EditMyInfoActivity.this, "비밀번호는 8-12 자리의 영문자와 소문자의 조합이어야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }


                // 이미지 수정
                editor.remove("profileImage");
                if(_userImage != null)
                    editor.putString("profileImage", ImageConverter.getImageToString(_userImage));
                else
                    editor.putString("profileImage", null);

                // 닉네임 수정
                editor.remove("nickname");
                editor.putString("nickname", _nickname.getText().toString());

                // 비밀번호 수정
                editor.remove("password");
                editor.putString("password", _password.getText().toString());

                // 지역구 수정
                editor.remove(residence);
                editor.putString("residence", areaSpinner.getSelectedItem().toString());

                //핸드폰 번호 수정
                editor.remove("phone_number");
                editor.putString("phone_number", _phonenumber.getText().toString());


                editor.commit();

                String data = getData(nickname, password, preferences.getString("profileImage",""), phonenumber, residence);
                String url;
                Intent intent = getIntent();
                if( (Boolean)intent.getExtras().get("isInstructor") )
                    url = "http://106.10.35.170/EditInstructor.php";
                else
                    url = "http://106.10.35.170/EditUser.php";

                NetworkTask networkTask = new NetworkTask(EditMyInfoActivity.this, url, data, 4);
                networkTask.execute();

                EditMyInfoActivity.this.finish();
            }
        });

    }

    public void SetProfileImage(View view) {
        try {
            if (ActivityCompat.checkSelfPermission(EditMyInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EditMyInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PICK_IMAGE);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, Constants.REQUEST_PICK_IMAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_PICK_IMAGE){
            CircleImageView imageView = findViewById(R.id.user_image);
            ImageProcessing imageProcessing = new ImageProcessing(EditMyInfoActivity.this);
            Uri imgUri = data.getData();
            _userImage = imageProcessing.SetImage(imageView, imgUri);
            //_userImage = imageProcessing.ConvertUriToBitmap(imgUri);

        }
    }

    public String getData(String nickname, String password, String userImage, String phonenumber, String residence){

        String data = "nickname=" + nickname + "&password=" + password + "&phone_number=" + phonenumber
                + "&residence=" + residence + "&email=" + email + "&image=" + userImage;

        Log.e("Image data", data);

        return data;
    }



    private void setArrayList(){

        arrayList = new ArrayList<String>();

        arrayList.add("동작구");
        arrayList.add("관악구");
        arrayList.add("서초구");
        arrayList.add("강남구");
        arrayList.add("송파구");
        arrayList.add("강동구");
        arrayList.add("광진구");
        arrayList.add("중랑구");
        arrayList.add("노원구");
        arrayList.add("도봉구");
        arrayList.add("강북구");
        arrayList.add("성북구");
        arrayList.add("동대문구");
        arrayList.add("성동구");
        arrayList.add("용산구");
        arrayList.add("중구");
        arrayList.add("종로구");
        arrayList.add("서대문구");
        arrayList.add("마포구");
        arrayList.add("은평구");
        arrayList.add("강서구");
        arrayList.add("양천구");
        arrayList.add("구로구");
        arrayList.add("영등포구");
        arrayList.add("금천구");

    }

    private int getPosition(String residence){

        for(int i = 0 ; i < arrayList.size() ; i++){
            if(residence.equals(arrayList.get(i))){
                return i;
            }
        }

        return 0;
    }
}
