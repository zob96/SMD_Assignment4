package com.example.smdassign3_q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    TextView back;
    TextView signin;
    Button signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    back= findViewById(R.id.back);
    signupbtn= findViewById(R.id.btn_signup);

    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            finish();
        }
    });


    signin= findViewById(R.id.txtSignin);
    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i= new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    });

    signupbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i= new Intent(getApplicationContext(), ContactList.class);
            startActivity(i);

        }
    });






    }






}
