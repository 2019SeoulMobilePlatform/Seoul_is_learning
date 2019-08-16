package com.example.clubactivity.Club;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubactivity.R;

public class ClubEnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_enter);

        Intent intent = getIntent();

        TextView title = findViewById(R.id.club_name);
        TextView description = findViewById(R.id.club_description);

        title.setText(intent.getExtras().getString("clubName"));
        description.setText(intent.getExtras().getString("clubDescription"));
    }

    public void JoinClub(View view) {

    }
}
