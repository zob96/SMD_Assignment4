package com.example.smdassign3_q1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        TextView contactName= findViewById(R.id.contact);

        String message= getIntent().getStringExtra("message");
        contactName.setText(message);


    }
}
