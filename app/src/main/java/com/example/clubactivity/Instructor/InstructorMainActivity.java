package com.example.clubactivity.Instructor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.Class.ClassDetailActivity;
import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Constants;
import com.example.clubactivity.MyPage.EditMyInfoActivity;
import com.example.clubactivity.Network.ImageConverter;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstructorMainActivity extends AppCompatActivity {

//    SwipeMenuListView instructorClassList;
    ListView instructorClassList;
    ChatViewAdapter instructorClassAdapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    CircleImageView user_image;
    TextView user_nickname;
    TextView user_residence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);
        instructorClassList = findViewById(R.id.instructor_class_listview);
        //instructorClassAdapter = new ChatViewAdapter() ;
        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        editor = preferences.edit();

        String url = "http://106.10.35.170/ImportInstructorClassList.php";
        String data = "email=" + preferences.getString("email","");
        NetworkTask networkTask = new NetworkTask(InstructorMainActivity.this, url, data, Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR, instructorClassAdapter);
//        NetworkTask networkTask = new NetworkTask(InstructorMainActivity.this, url, data, Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR);
        networkTask.execute();
        //instructorClassList.setAdapter(instructorClassAdapter);
//        SetListViewCreator(instructorClassList);

        //강사 정보 미리보기 세팅
        user_image = (CircleImageView)findViewById(R.id.user_image);
        user_nickname = findViewById(R.id.user_nickname);
        user_residence = findViewById(R.id.user_residence);
        if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
            user_image.setImageBitmap(getImageToBitmap(preferences.getString("profileImage", "")));
        else{
            user_image.setImageResource(R.drawable.ic_account_circle_white_60dp);
        }
        user_nickname.setText(preferences.getString("nickname", ""));
        user_residence.setText(preferences.getString("residence",""));


        //클래스 클릭해서 들어가기
        instructorClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(InstructorMainActivity.this, ClassDetailActivity.class);
                //Intent intent = new Intent(context, TabTest.class);

                ChatViewItem item = (ChatViewItem)instructorClassList.getAdapter().getItem(i);
                intent.putExtra("param", item.getTitle());
                intent.putExtra("desc", item.getDesc());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)item.getIcon()).getBitmap();

                Bitmap dstBitmap = Bitmap.createScaledBitmap(bitmap, Constants.IMAGE_SIZE, bitmap.getHeight()/(bitmap.getWidth()/Constants.IMAGE_SIZE), true);

                dstBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();


                intent.putExtra("area",item.getArea()); //클래스 지역구
                intent.putExtra("star",item.getStar()); //평점 뿌리기
                intent.putExtra("image",bytes);

                intent.putExtra("people", item.getPeople());
                intent.putExtra("location", item.getLocation());
                intent.putExtra("date", item.getDate());
                intent.putExtra("number", item.getPeopleNumber());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("favorite", item.getFavorite());
                intent.putExtra("class_index", item.getClass_index());
                intent.putExtra("number_now", item.getPeopleNumberNow()); //현재 인원수
                intent.putExtra("is_instructor", true);
                InstructorMainActivity.this.startActivity(intent);

                //startActivityForResult(intent, Constants.REQUEST_CLUB_INTRO_ENTER);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_class_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorMainActivity.this, AddClassActivity.class);
                startActivityForResult(intent, Constants.REQUSET_ADD_CLASS); // 요청한 곳을 구분하기 위한 숫자, 의미없음
            }
        });


        instructorClassList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long arg3) {

                DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        instructorClassAdapter = (ChatViewAdapter)instructorClassList.getAdapter();
                        ChatViewItem item = ((ChatViewItem)instructorClassAdapter.getItem(position));
                        //instructorClassAdapter.removeItem(position);

                        if( item.getNowMemberNum() == 0 ) {
                            String url = "http://106.10.35.170/DeleteClass.php";
                            String dataStr = "email=" + preferences.getString("email", "") + "&class_index=" + item.getClass_index();
                            NetworkTask networkTask = new NetworkTask(InstructorMainActivity.this, url, dataStr, Constants.SERVER_DELETE_CLASS);
                            networkTask.execute();
                            instructorClassAdapter.removeItem(position);

                        }
                        else {
                            Toast.makeText(InstructorMainActivity.this, "예약자가 있는 클래스는 삭제할 수 없습니다.", Toast.LENGTH_SHORT);
                        }
                        instructorClassAdapter.notifyDataSetChanged();
                    }
                };
                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(InstructorMainActivity.this)
                        .setTitle("해당 클래스를 삭제하시겠습니까?")
                        .setPositiveButton("예", positiveListener)
                        .setNegativeButton("취소", cancelListener).show();

                return true;
            }

        });
    }

    public void EditInfo(View view) {
        Intent intent = new Intent(InstructorMainActivity.this, EditMyInfoActivity.class);
        intent.putExtra("isInstructor", true);
        startActivityForResult(intent, Constants.REQUEST_EDIT_INFO_INS);
    }

//    public void SetListViewCreator(final SwipeMenuListView listView){
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(200);
//                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete_white_24dp);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//        // set creator
//        listView.setMenuCreator(creator);
//
//
//        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0:
//                        //예약자가 없을때/ 날짜가 지난 클래스일 때만 지우기 가능하게 // 어뎁터 새로만들어서 연결
//                        ChatViewItem wholeClubItem = (ChatViewItem)listView.getAdapter().getItem(position);
//
//                        ChatViewAdapter chatViewAdapter = (ChatViewAdapter)listView.getAdapter();
//                        chatViewAdapter.removeItem(position);
//
//                        chatViewAdapter.notifyDataSetChanged();
//                        break;
//                }
//                // false : close the menu; true : not close the menu
//                return false;
//            }
//        });
//    }

    public Bitmap getImageToBitmap(String encodedImage){
        byte[] decodedByte = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_EDIT_INFO_INS){
            if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
                user_image.setImageBitmap(getImageToBitmap(preferences.getString("profileImage", "")));
            else{
                user_image.setImageResource(R.drawable.ic_account_circle_white_60dp);
            }
            user_nickname.setText(preferences.getString("nickname", ""));
            user_residence.setText(preferences.getString("residence",""));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = "http://106.10.35.170/ImportInstructorClassList.php";
        String data = "email=" + preferences.getString("email","");
        NetworkTask networkTask = new NetworkTask(InstructorMainActivity.this, url, data, Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR, instructorClassAdapter);
        networkTask.execute();
    }
    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });

        alBuilder.setTitle("서울은 배우는중");
        alBuilder.setIcon(R.drawable.seoul_is_learning); //아이콘 설정
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
    }
}
