package com.example.smdassign3_q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView signup;
    Button   signinbtn;
    EditText editTextEmail;
    EditText editTextPassword;
    String actualPassword= "123123";
    String actualEmail= "zohaib9622@gmail.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup= findViewById(R.id.txtSignup);

        editTextEmail= findViewById(R.id.edttxt_email);
        editTextPassword= findViewById(R.id.edttxtPass);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }
        });



        signinbtn= findViewById(R.id.btn_login);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String email= editTextEmail.getText().toString();
              String password= editTextPassword.getText().toString();

              if(email.isEmpty() || password.isEmpty())
              {
                  Toast.makeText(getApplicationContext(), "Email or Password not entered. Try again", Toast.LENGTH_LONG).show();

              }

              else
              {
                  if(email.equals(actualEmail) && password.equals(actualPassword)) {

                      Intent i = new Intent(getApplicationContext(), ContactList.class);
                      startActivity(i);


                  }
                  else
                  {
                      Toast.makeText(getApplicationContext(), "Email or Password incorrect. Try again", Toast.LENGTH_LONG).show();

                  }


              }

            }
        });



    }




}
