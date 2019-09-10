package com.example.clubactivity.Network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Class.Item;
import com.example.clubactivity.Class.RecyclerAdapter;
import com.example.clubactivity.Class.ReviewListItem;
import com.example.clubactivity.Class.ReviewListViewAdapter;
import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Instructor.InstructorMainActivity;
import com.example.clubactivity.MainActivity;
import com.example.clubactivity.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;
    private int selection;

    public List<Item> items = new ArrayList<>();
    public ArrayList<ReviewListItem> reviewListItems = new ArrayList<>();
    ChatViewAdapter wholeClub_Adapter = null;
    ChatViewAdapter myClub_Adapter = null;


    public NetworkTask(Context _context, String url, String data, int action){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
    }
    public NetworkTask(Context _context, String url, int action){
        this.context = _context;
        this.url = url;
        this.selection = action;
        this.data = " ";
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
            switch(this.selection) {
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
                            String user_birth = jsonObject.getString("birth");
                            AppManager.getInstance().setEmail(user_email);

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
                            Log.e("Login", "성공");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "성공적으로 회원가입 되었습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
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
                            String user_birth = jsonObject.getString("birth");

                            SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();

                            editor.clear();
                            editor.commit();

                            editor.putString("email", user_email);
                            editor.putString("name", user_name);
                            editor.putString("password", user_password);
                            editor.putString("nickname", user_nickname);
                            editor.putString("phone_number", user_phonenumber);
                            editor.putString("residence", user_residence);
                            editor.putString("birth", user_birth);
                            editor.putString("profileImage", user_profile);

                            editor.commit();
                            this.context.startActivity(new Intent(this.context, InstructorMainActivity.class));
                            ((Activity) this.context).finish();

                            Toast.makeText(this.context, "로그인성공", Toast.LENGTH_LONG).show();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "성공적으로 변경했습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "변경에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                // 클래스 리스트 받아오기 5
                case Constants.SERVER_CLASS_LIST_GET:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        JSONArray resultObjectArray = new JSONArray(real_result);
                        if (!real_result.equals("fail")) {
                            JSONObject resultObject;

                           if(resultObjectArray.length() != 0) {

                               for(int i = 0 ; i < resultObjectArray.length(); i++){
                                   resultObject = resultObjectArray.getJSONObject(i);
                                   //Bitmap image = ImageConverter.getImageToBitmap(resultObject.getString("image")) ;
                                   byte[] decodedByte = Base64.decode(resultObject.getString("image"), Base64.DEFAULT);
                                   String name = resultObject.getString("name");
                                   String target_user = resultObject.getString("target_user");
                                   String address = resultObject.getString("address");
                                   String information = resultObject.getString("information");
                                   String time = resultObject.getString("time");
                                   String local = resultObject.getString("local");
                                   int count_max = resultObject.getInt("count_max");
                                   String count = String.valueOf(resultObject.getInt("count")); //바꾸자
                                   float star = (float)resultObject.getDouble("star");
                                   String price = String.valueOf(resultObject.getInt("price"));
                                   int class_index = resultObject.getInt("class_index");

                                   Item item = new Item(class_index ,decodedByte, star,name,information,local,target_user,address,time,count,price);
                                   items.add(item);
                               }

                               //클래스 리스트 설정 Recyclerview
                               RecyclerView recyclerView = (RecyclerView) ((Activity) context).findViewById(R.id.class_list);
                               recyclerView.setAdapter(new RecyclerAdapter(context, items, R.layout.class_list));
                               LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                               recyclerView.setHasFixedSize(true);
                               recyclerView.setLayoutManager(layoutManager);
                           }
                           else
                           {
                               Toast.makeText(this.context, "해당 클래스가 아직 없습니다.", Toast.LENGTH_LONG).show();
                           }
                        }
                        else{
                            Toast.makeText(this.context, "클래스 내용이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                    //서버에서 클래스 리뷰 가져오기
                case Constants.SERVER_CLASS_REVIEW_GET:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        JSONArray resultObjectArray = new JSONArray(real_result);
                        if (!real_result.equals("fail")) {
                            JSONObject resultObject;
                            if(resultObjectArray.length() != 0) {
                                for(int i = 0 ; i < resultObjectArray.length(); i++){
                                    resultObject = resultObjectArray.getJSONObject(i);

                                    byte[] decodedByte = Base64.decode(resultObject.getString("image"), Base64.DEFAULT);
                                    String nickname = resultObject.getString("nickname");
                                    String review = resultObject.getString("review");
                                    float star = (float)resultObject.getDouble("star");

                                    ReviewListItem item = new ReviewListItem(decodedByte ,nickname,star,review);
                                    reviewListItems.add(item);
                                }

                                //후기 설정
                                ReviewListViewAdapter adapter;  //후기 리스트뷰 어댑터
                                ListView reviewList;         // 후기 리스트
                                reviewList = (ListView) ((Activity) context).findViewById(R.id.class_review_listView);
                                adapter = new ReviewListViewAdapter(((Activity) context).getLayoutInflater(), R.layout.class_review_listview_item, reviewListItems);
                                reviewList.setAdapter(adapter);
                            }
                        }
                        else{
                            Toast.makeText(this.context, "클래스 리뷰가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                case Constants
                        .SERVER_CLASS_ADD_CLASS:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "성공적으로 추가하였습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "추가에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        JSONArray resultObjectArray = new JSONArray(real_result);
                        wholeClub_Adapter = new ChatViewAdapter();
                        if (!real_result.equals("fail")) {
                            JSONObject resultObject;
                            if (resultObjectArray.length() != 0) {
                                for (int i = 0; i < resultObjectArray.length(); i++) {
                                    resultObject = resultObjectArray.getJSONObject(i);
                                    Bitmap image = ImageConverter.getImageToBitmap(resultObject.getString("image"));
                                    Drawable drawable = new BitmapDrawable(image);
                                    String name = resultObject.getString("name");
                                    String information = resultObject.getString("information");
                                    int count_max = resultObject.getInt("count_max");
                                    int count = resultObject.getInt("count");
                                    int room_index = resultObject.getInt("room_index");
                                    wholeClub_Adapter.addItem(drawable, name, information, count_max, count, room_index);
                                }
                            }
                            AppManager.getInstance().setWholeClub_Adapter(wholeClub_Adapter.getChatViewItemList());
                        } else {
                            Toast.makeText(this.context, "동호회 내용이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        JSONArray resultObjectArray = new JSONArray(real_result);
                        myClub_Adapter = new ChatViewAdapter();
                        if (!real_result.equals("fail")) {
                            JSONObject resultObject;
                            if (resultObjectArray.length() != 0) {
                                for (int i = 0; i < resultObjectArray.length(); i++) {
                                    resultObject = resultObjectArray.getJSONObject(i);
                                    Bitmap image = ImageConverter.getImageToBitmap(resultObject.getString("image"));
                                    Drawable drawable = new BitmapDrawable(image);
                                    String name = resultObject.getString("name");
                                    String information = resultObject.getString("information");
                                    int count_max = resultObject.getInt("count_max");
                                    int count = resultObject.getInt("count");
                                    int room_index = resultObject.getInt("room_index");
                                    myClub_Adapter.addItem(drawable, name, information, count_max, count, room_index);
                                }
                            }
                            AppManager.getInstance().setMyClub_Adapter(myClub_Adapter.getChatViewItemList());
                        } else {
                            Toast.makeText(this.context, "동호회 내용이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.SERVER_CLASS_RESERVATION:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "예약되었습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "예약 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
