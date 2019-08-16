package com.example.clubactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChatRoomActivity extends AppCompatActivity {

    Button sendButton ;
    MessageListAdapter adapter;
    TextView messageTextView;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        adapter = new MessageListAdapter();
        messageTextView = findViewById(R.id.editText);


        listview = (ListView) findViewById(R.id.chatmessage_listView);
        listview.setAdapter(adapter);


        sendButton = findViewById(R.id.send_text_btn);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //adapter.addItem(messageTextView.getText().toString());
                //attemptSend();
                Toast.makeText(ChatRoomActivity.this, messageTextView.getText(),Toast.LENGTH_LONG).show();

                adapter.addItem(messageTextView.getText().toString(), 1);
                messageTextView.setText("");
                adapter.notifyDataSetChanged();
            }
        });


    }


}
