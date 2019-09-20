package com.example.clubactivity.Club;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Constants;
import com.example.clubactivity.ImageProcessing;
import com.example.clubactivity.Network.ImageConverter;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity{

    Button sendButton ;
    MessageListAdapter adapter;
    TextView messageTextView;
    ListView listview;
    int room_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        Log.e("chatroom","입장");

        Intent intent = getIntent();
        TextView title = findViewById(R.id.club_chatting_title);

        title.setText(intent.getExtras().get("clubName").toString());
        //adapter = new MessageListAdapter(this, (ArrayList<MessageListAdapter.MessageContents>) intent.getExtras().get("chatList"));

        messageTextView = findViewById(R.id.editText);

        listview = (ListView) findViewById(R.id.chatmessage_listView);
        //listview.setAdapter(adapter);


        String url = "http://106.10.35.170/ImportMessageList.php";
        String data = getData(AppManager.getInstance().getEmail(), intent.getIntExtra("chatIndex", 0));
        NetworkTask networkTask = new NetworkTask(ChatRoomActivity.this, url, data, Constants.IMPORT_MESSAGELIST);
        networkTask.execute();

        adapter = (MessageListAdapter)listview.getAdapter();

        sendButton = findViewById(R.id.send_text_btn);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //adapter.addItem(messageTextView.getText().toString());
                //attemptSend();
                if(!messageTextView.getText().toString().isEmpty()){
                    String url = "http://106.10.35.170/StoreMessage.php";
                    String data = getData(messageTextView.getText().toString(),AppManager.getInstance().getEmail(), room_index);
                    NetworkTask networkTask = new NetworkTask(view.getContext(), url, data, Constants.SEND_MESSAGE);
                    networkTask.execute();
                    adapter.addItem(messageTextView.getText().toString(), 0, "id");
                    messageTextView.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bitmap image = adapter.getImage(i);
                if(image != null){
                    Intent intent = new Intent(ChatRoomActivity.this, FullScreenImageActivity.class);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap dstBitmap = Bitmap.createScaledBitmap(image, Constants.IMAGE_SIZE, image.getHeight()/(image.getWidth()/Constants.IMAGE_SIZE), true);

                    dstBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] bytes = stream.toByteArray();
                    intent.putExtra("chatImage",bytes);

                    startActivity(intent);
                }
            }
        });
    }

    public String getData(String message, String email, int room_index){

        String data = "room_index=" + room_index + "&user_email=" +email + "&message=" + message;

        return data;
    }

    public String getData(Bitmap image, String email, int room_index){

        String message_image = ImageConverter.getImageToString(image);

        String data = "room_index=" + room_index + "&user_email=" +email + "&message_image=" + message_image ;

        return data;
    }


    public void SendImage(View view) {
        try {
            if (ActivityCompat.checkSelfPermission(ChatRoomActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ChatRoomActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PICK_IMAGE);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, Constants.REQUEST_PICK_IMAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        //Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.setType("image/*");

        //startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), Constants.REQUEST_GET_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_PICK_IMAGE){

            Uri imgUri = data.getData() ;
            ImageProcessing imageProcessing = new ImageProcessing(ChatRoomActivity.this);
            Bitmap image = imageProcessing.ConvertRareUriToBitmap(imgUri);

/*
            Bitmap image = null;
            Bitmap originalImage = null;
            //String imagePath = getRealPathFromURI(data.getData());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inSampleSize = 4;

            //String image
            //ImageView imageView = findViewById(R.id.chat_image);
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Matrix matrix = new Matrix();
                matrix.postRotate(0);
                originalImage = BitmapFactory.decodeStream(inputStream);
                //Bitmap originalBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                image = Bitmap.createBitmap(originalImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), matrix, true);

            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            //Toast.makeText(this, imagePath, Toast.LENGTH_LONG).show();
            //adapter.addItem(1, imagePath);
*/
            String url = "http://106.10.35.170/StoreMessage.php";
            String data_image = getData(image, AppManager.getInstance().getEmail(), room_index);
            NetworkTask networkTask = new NetworkTask(this, url, data_image, Constants.SEND_MESSAGE);
            networkTask.execute();

            adapter.addItem(0, image, "id");
            adapter.notifyDataSetChanged();

        }
    }

    //URI 실제 경로 얻는 함수
    private String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        String realPath = cursor.getString(column_index);
        return realPath;
    }


    public String getData(String email, int room_index){

        String data = "email=" + email + "&room_index=" + room_index;

        return data;
    }

}
