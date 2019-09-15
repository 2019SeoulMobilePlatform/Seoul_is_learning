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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.clubactivity.AppManager;
import com.example.clubactivity.Class.Item;
import com.example.clubactivity.Class.RecyclerAdapter;
import com.example.clubactivity.Class.ReviewListItem;
import com.example.clubactivity.Class.ReviewListViewAdapter;
import com.example.clubactivity.Club.ChatRoomActivity;
import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Club.MessageListAdapter;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Instructor.InstructorMainActivity;
import com.example.clubactivity.MainActivity;
import com.example.clubactivity.MyPage.RecyclerViewAdapter;
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
    private Button btn;
    private String title;
    private Activity activity;

    public List<Item> items = new ArrayList<>();
    public ArrayList<ChatViewItem> chatViewItems = new ArrayList<>();
    public ArrayList<ReviewListItem> reviewListItems = new ArrayList<>();
    ChatViewAdapter wholeClub_Adapter = null;
    ChatViewAdapter myClub_Adapter = null;
    ChatViewAdapter instructor_Adapter = null;
    MessageListAdapter chatList;

    RecyclerViewAdapter myClass_Adapter = null;

    public NetworkTask(Context _context, String url, String data, int action, RecyclerViewAdapter recyclerViewAdapter){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        this.myClass_Adapter = recyclerViewAdapter;
    }

    public NetworkTask(Context _context, String url, String data, int action, ChatViewAdapter chatViewAdapter){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        this.instructor_Adapter = chatViewAdapter;
    }

    public NetworkTask(Context _context, String url, String data, int action, Button btn){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        this.btn = btn;
    }

    public NetworkTask(Context _context, String url, String data, int action, String title, Activity activity){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        this.title = title;
        this.activity = activity;
    }

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
    protected void onPostExecute(String result) {

        try {
            //경우에 따라 결과 값을 받아 일어났으면 하는 작업
            switch (this.selection) {
                case Constants.USER_LOGIN:
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

                case Constants.SIGNUP:
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

                case Constants.INSTRUCTOR_LOGIN:
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

                case Constants.EDIT_MYPAGE:
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
                case Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR:
                case Constants.SERVER_GET_MY_CLASS:
                case Constants.SERVER_GET_FAVORITE_CLASS:
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
                                   String count_max = String.valueOf(resultObject.getInt("count_max"));
                                   String count = String.valueOf(resultObject.getInt("count"));
                                   float star = (float)resultObject.getDouble("star");
                                   String price = String.valueOf(resultObject.getInt("price"));
                                   int class_index = resultObject.getInt("class_index");
                                   boolean favorite = resultObject.getBoolean("favorite");
                                   int flag_dongnae = resultObject.getInt("flag"); //0 은 그냥 1이 동네배움터

                                   if(this.selection == Constants.SERVER_CLASS_LIST_GET || this.selection == Constants.SERVER_GET_MY_CLASS || this.selection == Constants.SERVER_GET_FAVORITE_CLASS){
                                       Item item = new Item(class_index ,decodedByte, star,name,information,local,target_user,address,time,count, count_max, price, favorite,flag_dongnae);
                                       items.add(item);
                                       Log.d("ㅜㅜ", item.getTitle());
                                   }
                                   if(selection == Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR){
                                       ChatViewItem chatViewItem = new ChatViewItem(class_index ,decodedByte, star,name,information,local,target_user,address,time,count,count_max,price, false,flag_dongnae);
                                       chatViewItems.add(chatViewItem);
                                       Log.d("ㅜㅅㅜ", chatViewItem.getTitle());
                                   }
                               }
                               if(selection == Constants.SERVER_CLASS_LIST_GET){
                               //클래스 리스트 설정 Recyclerview
                                   RecyclerView recyclerView = (RecyclerView) ((Activity) context).findViewById(R.id.class_list);
                                   recyclerView.setAdapter(new RecyclerAdapter(context, items, R.layout.class_list));
                                   LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                                   recyclerView.setHasFixedSize(true);
                                   recyclerView.setLayoutManager(layoutManager);
                               }
                               else if(selection == Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR){
//                                   SwipeMenuListView instructorClassList = ((Activity) context).findViewById(R.id.instructor_class_listview);
                                   ListView instructorClassList = ((Activity) context).findViewById(R.id.instructor_class_listview);
//                                   instructor_Adapter.setChatViewItemList(chatViewItems);
//                                   instructorClassList.setAdapter(instructor_Adapter);
                                   instructorClassList.setAdapter(new ChatViewAdapter(chatViewItems));
                                   Log.d("살려줘", instructor_Adapter.getChatViewItemList().get(0).getTitle());
                               }
                               else if(selection == Constants.SERVER_GET_MY_CLASS){
                                   RecyclerView recyclerView = ((Activity) context).findViewById(R.id.myclass_recyclerView);
                                   recyclerView.setAdapter(new RecyclerViewAdapter(context, (ArrayList)items));
                                   LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                                   recyclerView.setLayoutManager(layoutManager);
                                   TextView noClass;
                                   if(items.size() != 0) {
                                       noClass = (TextView) (((Activity) context).findViewById(R.id.text_no_my_class));
                                       noClass.setVisibility(View.GONE);
                                   }
                               }
                               else if(selection == Constants.SERVER_GET_FAVORITE_CLASS){
                                   RecyclerView recyclerView = ((Activity) context).findViewById(R.id.favorite_recyclerView);
                                   recyclerView.setAdapter(new RecyclerViewAdapter(context, (ArrayList)items));
                                   LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                                   recyclerView.setLayoutManager(layoutManager);
                                   TextView noClass;
                                   if(items.size() != 0) {
                                       noClass = (TextView) (((Activity) context).findViewById(R.id.text_no_favorite_class));
                                       noClass.setVisibility(View.GONE);
                                   }
                               }
                           }
                           else
                           {
                               Toast.makeText(this.context, "해당 클래스가 아직 없습니다.", Toast.LENGTH_LONG).show();

                           }
                        }
                        /*
                        else{
                            Toast.makeText(this.context, "클래스 내용이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }*/
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
                            if (resultObjectArray.length() != 0) {
                                for (int i = 0; i < resultObjectArray.length(); i++) {
                                    resultObject = resultObjectArray.getJSONObject(i);

                                    byte[] decodedByte = Base64.decode(resultObject.getString("image"), Base64.DEFAULT);
                                    String nickname = resultObject.getString("nickname");
                                    String review = resultObject.getString("review");
                                    float star = (float) resultObject.getDouble("star");

                                    ReviewListItem item = new ReviewListItem(decodedByte, nickname, star, review);
                                    reviewListItems.add(item);
                                }

                                //후기 설정
                                ReviewListViewAdapter adapter;  //후기 리스트뷰 어댑터
                                ListView reviewList;         // 후기 리스트
                                reviewList = (ListView) ((Activity) context).findViewById(R.id.class_review_listView);
                                adapter = new ReviewListViewAdapter(((Activity) context).getLayoutInflater(), R.layout.class_review_listview_item, reviewListItems);
                                reviewList.setAdapter(adapter);
                            }
                        } else if (real_result.equals("fail1")) {
                            Toast.makeText(this.context, "룸유저에 추가 실패하였습니다.", Toast.LENGTH_LONG).show();
                        } else if (real_result.equals("fail2")) {
                            Toast.makeText(this.context, "찾기에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "클래스 리뷰가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case Constants.SERVER_CLASS_ADD_CLASS:
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
                case Constants.GET_WHOLECLUBLIST:
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
                case Constants.GET_MYCLUBLIST:
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
                    break;
                case Constants.SERVER_CLASS_ADD_FAVORITE:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "찜 목록에 추가되었습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "찜 목록 추가에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.SERVER_CLASS_DELETE_FAVORITE:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "찜 목록에서 삭제되었습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "찜 목록에서 삭제하지 못했습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.SERVER_CHECK_DUPLICATE_EMAIL:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "사용 가능한 이메일입니다.", Toast.LENGTH_LONG).show();
                            btn.setEnabled(false);
                        } else {
                            Toast.makeText(this.context, "이미 회원 가입 된 이메일입니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.ENTER_CLUB:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "동호회방 입장에 성공하였습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "동호회방 입장에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case Constants.REMOVE_CLUB:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "동호회방 지우기에 성공하였습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "동호회방 지우기에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.SERVER_DELETE_CLASS:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        if (real_result.equals("success")) {
                            Toast.makeText(this.context, "클래스 삭제 성공", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "클래스 삭제 실패", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.IMPORT_MESSAGELIST:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String real_result = jsonObject.getString("result");
                        JSONArray resultObjectArray = new JSONArray(real_result);
                        chatList = new MessageListAdapter(context);

                        if(real_result.equals("empty")){
                            Toast.makeText(this.context, "대화 내용이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                        }
                        else if (real_result.equals("fail")) {
                            Toast.makeText(this.context, "연결 실패", Toast.LENGTH_LONG).show();
                        } else {
                            JSONObject resultObject;

                            if (resultObjectArray.length() != 0) {
                                for (int i = 0; i < resultObjectArray.length(); i++) {
                                    resultObject = resultObjectArray.getJSONObject(i);
                                    Bitmap profile = ImageConverter.getImageToBitmap(resultObject.getString("profile"));
                                    String nickname = resultObject.getString("nickname");
                                    int flag = resultObject.getInt("flag");
                                    String user_id = resultObject.getString("email");
                                    if (resultObject.isNull("image")) {
                                        String message = resultObject.getString("message");
                                        chatList.addItem(message, flag, user_id, nickname, profile);
                                    } else {
                                        Bitmap messageImage = ImageConverter.getImageToBitmap(resultObject.getString("image"));
                                        chatList.addItem(flag, messageImage, user_id, nickname, profile);
                                    }
                                }
                            }
                        }

                        Intent intent = new Intent(activity, ChatRoomActivity.class);
                        intent.putExtra("clubName", title);
                        intent.putExtra("chatList", chatList.getMessages());
                        activity.startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
