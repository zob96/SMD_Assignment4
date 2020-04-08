package com.example.smdassign3_q1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContact extends AppCompatActivity {

    Button saveBtn;
    Button addimgBtn;
    ImageView contactImg;
    public static final int PERMISSION_CODE=1001;
    public static final int IMAGE_PICK_CODE=1000;
    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;
    private  static int k=0;
    private byte[] selectedimg;
    ArrayList<EditText> etArray = new ArrayList<>(20);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Log.d("SHOW", "ON CREATE");


        mLayout = (LinearLayout) findViewById(R.id.linearlayout);
        mEditText = (EditText) findViewById(R.id.contactNumEdt);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView(this);
        textView.setText("New text");






    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.addView(createNewTextView());
            }
        };
    }

    private EditText createNewTextView() {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(550, LinearLayout.LayoutParams.WRAP_CONTENT);
        final EditText editText = new EditText(this);
        editText.setLayoutParams(lparams);
        etArray.add(k,editText);


        k++;

       // editText.setText("New text: " + text);
        return editText;
    }

    @Override
    protected void onStart() {
        super.onStart();

        saveBtn= findViewById(R.id.savbtn);
        addimgBtn= findViewById(R.id.addimgBtn);
        contactImg= findViewById(R.id.contactimg);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText firstnameedt= findViewById(R.id.firstNameEdt);
                EditText lastnameedt= findViewById(R.id.lastNameEdt);
                EditText contactnumedt= findViewById(R.id.contactNumEdt);
                EditText emailedt= findViewById(R.id.emailEdt);

                String firstname=firstnameedt.getText().toString();
                String lastname=lastnameedt.getText().toString();
                String contactnum=contactnumedt.getText().toString();
                String email=emailedt.getText().toString();

                Boolean flag=false;


                String encodedImageString="";

                if(firstname!= null && lastname!=null && contactnum!=null && email!=null && !firstname.isEmpty() && !lastname.isEmpty() && !contactnum.isEmpty() && !email.isEmpty())
                {

                         /*
                    if(contactImg!=null) {
                        Bitmap bitmap;

                        bitmap = ((BitmapDrawable) contactImg.getDrawable()).getBitmap();

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();

                        encodedImageString = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    }
                    else
                    {

                    }

            */






                    Contact newContact= new Contact();
                    newContact.setContact_firstname(firstname);
                    newContact.setContact_lastname(lastname);
                    newContact.setContact_number(contactnum);
                    newContact.setContact_email(email);
                    if(selectedimg!=null && selectedimg.length!=0)
                    {
                        newContact.setImage(selectedimg);
                    }

                    ContactList.myAppDatabase.myDao().insertContact(newContact);

                    Contact recent=  ContactList.myAppDatabase.myDao().getLastInsertedContact();
                    int id= recent.getContact_id();

                    Log.d("ID", " "+ id);


                    String mynum= recent.getContact_number();
                    Number mynumber= new Number();

                    mynumber.setContact_id(id);
                    mynumber.setContactnumber(mynum);

                    ContactList.myAppDatabase.myDao().insertNumber(mynumber);



                    int size= etArray.size();
                    Log.d("EDT", ""+size);

                    for(int i=0; i<size; i++)
                    {


                        EditText myedt= (EditText) etArray.get(i);
                        String mytxt= myedt.getText().toString();
                        Number newnumber= new Number();
                        newnumber.setContactnumber(mytxt);
                        newnumber.setContact_id(id);

                        ContactList.myAppDatabase.myDao().insertNumber(newnumber);


                        int myid= newnumber.getContact_id();
                        Log.d("EDT", mytxt+","+myid);
                    }







                    String name= recent.getContact_firstname() + recent.getContact_lastname();
                    createNotificationChaneel(name);

                    Intent intent=new Intent();
                    setResult(2,intent);finish();




                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Some fields were left empty. Couldn't write to database. Try again", Toast.LENGTH_LONG).show();
                }




            }

        });


        addimgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {

                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {

                        String [] permissions= {Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions,PERMISSION_CODE);

                    }

                    else
                    {

                        pickImageFromGallery();


                    }




                }

                else
                {

                }


            }
        });



    }


    public void createNotificationChaneel(String contact_name)
    {

        String name= "Contacts Channel";
        String channelid= "1";
        int importance= NotificationManager.IMPORTANCE_HIGH;

        int notification_id= 1122;

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {

            NotificationChannel contactsChannel = new NotificationChannel(channelid, name, importance);

            contactsChannel.enableLights(true);
            contactsChannel.setLightColor(Color.BLUE);
            contactsChannel.enableVibration(true);
            contactsChannel.setShowBadge(true);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(contactsChannel);
            }

        }


    /*
        Intent intent = new Intent(AddContact.this, NotificationActivity.class);

        TaskStackBuilder stackBuilder= TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(intent);
        */

        //PendingIntent pendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);



            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelid)
                    .setContentTitle("Contact Added")
                    .setContentText(contact_name+" has been added to your Contacts.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                   // .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(android.R.color.holo_blue_light));








            if(notificationManager!=null)
            {
                notificationManager.notify(notification_id, builder.build());
            }




        }




    public void pickImageFromGallery()
        {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");

            startActivityForResult(intent, IMAGE_PICK_CODE);



        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){

            case PERMISSION_CODE:{

                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    pickImageFromGallery();
                }

                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }

            }



        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK && requestCode== IMAGE_PICK_CODE)
        {
           // Toast.makeText(this, "IMAGE SAVED", Toast.LENGTH_LONG).show();

            contactImg.setImageURI(data.getData());

            Bitmap bitmapImage;
            bitmapImage = ((BitmapDrawable) contactImg.getDrawable()).getBitmap();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            selectedimg = stream.toByteArray();

//                  if (bitmapImage != null && !bitmapImage.isRecycled())
//            {
//                bitmapImage.recycle();
//
//            }

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath=new File(directory,"recentContact.jpg");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



            /*

            byte[] decodedString = Base64.decode(encoded, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            contactImg.setImageBitmap(decodedByte);
                */




        }


    }
}






