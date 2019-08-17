package com.example.clubactivity.Club;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.Constants;
import com.example.clubactivity.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ChatRoomActivity extends AppCompatActivity {

    Button sendButton ;
    MessageListAdapter adapter;
    TextView messageTextView;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        adapter = new MessageListAdapter(this);
        messageTextView = findViewById(R.id.editText);


        listview = (ListView) findViewById(R.id.chatmessage_listView);
        listview.setAdapter(adapter);


        sendButton = findViewById(R.id.send_text_btn);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //adapter.addItem(messageTextView.getText().toString());
                //attemptSend();
                if(!messageTextView.getText().toString().isEmpty()){
                    adapter.addItem(messageTextView.getText().toString(), 0, "id");
                    messageTextView.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }


    public void SendImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        //Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), Constants.REQUEST_GET_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_GET_IMAGE){

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
                matrix.postRotate(90);
                originalImage = BitmapFactory.decodeStream(inputStream);
                //Bitmap originalBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                image = Bitmap.createBitmap(originalImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), matrix, true);

            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            //Toast.makeText(this, imagePath, Toast.LENGTH_LONG).show();
            //adapter.addItem(1, imagePath);
            adapter.addItem(1, image, "id");
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


}
