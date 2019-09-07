package com.example.clubactivity.Network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.clubactivity.Constants;
import com.example.clubactivity.InstructorMainActivity;
import com.example.clubactivity.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;
    private int selection;

    public NetworkTask(Context _context, String url, String data, int action){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;

        try {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.Request(url, data);

        }catch (Exception e){
            result = "Error";
        }

        return result;
    }

    @Override
    protected void onPreExecute(){
        // 상태에 맞는 안내문 넣어주는 부분
        // 메인스레드에서는 응답을 기다리는 안내 메세지를 띄워주면 될 것같음
    }

    @Override
    protected void onPostExecute(String result){

        try{
            //경우에 따라 결과 값을 받아 일어났으면 하는 작업
            switch(this.selection){
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String user_info = jsonObject.getString("result");
                        if (user_info.equals("fail")) {
                            Toast.makeText(this.context, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            String user_email = jsonObject.getString("email");
                            String user_name = jsonObject.getString("name");
                            String user_password = jsonObject.getString("password");
                            String user_nickname = jsonObject.getString("nickname");
                            String user_phonenumber = jsonObject.getString("phone_number");
                            String user_residence = jsonObject.getString("residence");
                            String user_profile = jsonObject.getString("image");
                            Log.e("getdata",user_profile);
                            String user_birth = jsonObject.getString("birth");

                            SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor user_editor = preferences.edit();

                            user_editor.clear();
                            user_editor.commit();

                            user_editor.putString("email", user_email);
                            user_editor.putString("name", user_name);
                            user_editor.putString("password", user_password);
                            user_editor.putString("nickname", user_nickname);
                            user_editor.putString("phone_number", user_phonenumber);
                            user_editor.putString("residence", user_residence);
                            user_editor.putString("birth", user_birth);
                            user_editor.putString("profileImage", user_profile);


                            user_editor.commit();

                            this.context.startActivity(new Intent(this.context, MainActivity.class));
                            ((Activity) this.context).finish();
                            Toast.makeText(this.context, "로그인성공", Toast.LENGTH_LONG).show();

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try{
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if(real_result.equals("success")){
                            Toast.makeText(this.context, "성공적으로 회원가입 되었습니다.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(this.context, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String instructor_info = jsonObject.getString("result");
                        if (instructor_info.equals("fail")) {
                            Toast.makeText(this.context, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            String user_email = jsonObject.getString("email");
                            String user_name = jsonObject.getString("name");
                            String user_password = jsonObject.getString("password");
                            String user_nickname = jsonObject.getString("nickname");
                            String user_phonenumber = jsonObject.getString("phone_number");
                            String user_residence = jsonObject.getString("residence");
                            String user_profile = jsonObject.getString("image");
                            Log.e("login image", user_profile);
                            String user_birth = jsonObject.getString("birth");


                            SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor instructor_editor = preferences.edit();

                            instructor_editor.clear();
                            instructor_editor.commit();

                            instructor_editor.putString("email", user_email);
                            instructor_editor.putString("name", user_name);
                            instructor_editor.putString("password", user_password);
                            instructor_editor.putString("nickname", user_nickname);
                            instructor_editor.putString("phone_number", user_phonenumber);
                            instructor_editor.putString("residence", user_residence);
                            instructor_editor.putString("birth", user_birth);
                            instructor_editor.putString("profileImage", user_profile);

                            instructor_editor.commit();
                            this.context.startActivity(new Intent(this.context, InstructorMainActivity.class));
                            ((Activity) this.context).finish();

                            Toast.makeText(this.context, "로그인성공", Toast.LENGTH_LONG).show();

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try{
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if(real_result.equals("success")){
                            Toast.makeText(this.context, "성공적으로 변경했습니다.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(this.context, "변경에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    break;

                case 5:
                    try{
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        JSONArray resultObjectArray = new JSONArray(real_result);
                        if(!real_result.equals("fail")) {
                            JSONObject resultObject;
                           if(resultObjectArray.length() != 0) {
                               for(int i = 0 ; i < resultObjectArray.length(); i++){
                                   resultObject = resultObjectArray.getJSONObject(i);
                                   Bitmap image = ImageConverter.getImageToBitmap(resultObject.getString("image")) ;
                                   String name = resultObject.getString("name");
                                   String target_user = resultObject.getString("target_user");
                                   String address = resultObject.getString("address");
                                   String information = resultObject.getString("information");
                                   String local = resultObject.getString("local");
                                   int count_max = resultObject.getInt("count_max");
                                   int count = resultObject.getInt("count");
                                   double star = resultObject.getDouble("star");
                                   int price = resultObject.getInt("price");
                               }
                           }
                        }
                        else{
                            Toast.makeText(this.context, "클래스 내용이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
