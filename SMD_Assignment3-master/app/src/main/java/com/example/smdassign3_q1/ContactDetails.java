package com.example.smdassign3_q1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactDetails extends AppCompatActivity {

    String name;
    String number;
    String email;
    Bitmap image;
    int id;
    TextView textViewName;
    TextView textViewNumber;
    TextView textViewEmail;
    ImageView imageView;
    private  static int k=0;
    private LinearLayout mLayout;
    ArrayList<TextView> tvArray = new ArrayList<>(20);
    List<String> allnums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        textViewName = findViewById(R.id.contact_name);
        textViewEmail = findViewById(R.id.contact_email);
        textViewNumber = findViewById(R.id.contact_number);
        imageView = findViewById(R.id.ContactImg);
        mLayout= findViewById(R.id.ll);
        TextView textView = new TextView(this);
        textView.setText("New text");

        id= getIntent().getIntExtra("id", id);
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        email = getIntent().getStringExtra("email");
     //   byte[] tempimg= getIntent().getByteArrayExtra("image");

     //   image = BitmapFactory.decodeByteArray(tempimg, 0, tempimg.length);


        Contact thiscontact= ContactList.myAppDatabase.myDao().getContactById(id);

       byte[] byteimg= thiscontact.getImage();



        if(byteimg!=null && byteimg.length!=0) {
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteimg);
            image = BitmapFactory.decodeStream(arrayInputStream);

        }
        else
        {
            image= BitmapFactory.decodeResource(getResources(), R.drawable.defaulticon);
        }


       allnums = ContactList.myAppDatabase.myDao().getNumbersOfContactById(id);


        textViewName.setText(name);
        textViewNumber.setText(number);
        textViewEmail.setText(email);
        imageView.setImageBitmap(image);



        getIntent().removeExtra("name");

        if (allnums != null && !allnums.isEmpty()) {


            for(String num: allnums)
            {
                if(num.equals(number))
                {

                }
                else {
                    showNumber(num);
                    Log.d("ALL NUMS", num);
                }
            }


        }
        else
        {
            Log.d("ALL NUMS", "nullllllllll");
        }


    }

    private void showNumber(String nm) {

        mLayout.addView(createNewTextView(nm));


    }

    private TextView createNewTextView(String n) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(550, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);

        lparams.setMargins(120, 20, 0, 0);

        textView.setLayoutParams(lparams);
      //  textView.setCompoundDrawables(getDrawable(getDrawable()), null, null, null);
        textView.setText(n);
        textView.setTextColor(getResources().getColor(R.color.BLACK));


        tvArray.add(k,textView);


      //  k++;

        // editText.setText("New text: " + text);
        return textView;
    }





}
