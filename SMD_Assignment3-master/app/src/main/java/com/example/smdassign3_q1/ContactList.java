package com.example.smdassign3_q1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {

    Button addContactBtn;

    public static MyAppDatabase myAppDatabase;
    ArrayList <ProfilePosts> allPosts;
    MyAdapter myAdapter;
    TextView noContactsTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FLOW", "On Create");


        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "mydb").allowMainThreadQueries().build();

        setContentView(R.layout.activity_contact_list);

        RecyclerView recyclerView = findViewById(R.id.rv);


        recyclerView.setLayoutManager( new LinearLayoutManager(this));



/*
        ProfilePosts post1 = new ProfilePosts(R.drawable.hassan, "Hassan Hameed", "03228766598", "hassan56@gmail.com");
        ProfilePosts post2 = new ProfilePosts(R.drawable.uzair, "Uzair Rehman", "03335689899", "uzair88@gmail.com");
        ProfilePosts post3 = new ProfilePosts(R.drawable.zohaib, "Zohaib Nafees", "03457777788", "mustafa.ift86@gmail.com");
        ProfilePosts post4 = new ProfilePosts(R.drawable.maarij, "Maarij Fatima", "03023377339", "maarij77@gmail.com");
        ProfilePosts post5 = new ProfilePosts(R.drawable.hsn, "Hassan Minhas", "03228899456", "hassan.minhas55@gmail.com");
        ProfilePosts post6 = new ProfilePosts(R.drawable.rimsha, "Rimsha Gul", "03348899456", "rimsha.gul55@gmail.com");
*/
  //      ProfilePosts [] posts = {post1,post2,post3,post4,post5};

       allPosts = new ArrayList<ProfilePosts>();
    /*
        allPosts.add(post1);
        allPosts.add(post2);
        allPosts.add(post3);
        allPosts.add(post4);
        allPosts.add(post5);
        allPosts.add(post6);
*/

         myAdapter= new MyAdapter(allPosts, this);
        recyclerView.setAdapter(myAdapter);


        noContactsTxt= (TextView)findViewById(R.id.noContactTxt);

        if(noContactsTxt!=null) {
            noContactsTxt.setVisibility(View.GONE);
        }


        List<Contact> contacts= myAppDatabase.myDao().getContacts();

        if(contacts!=null && !contacts.isEmpty()) {
            for (Contact contact : contacts) {
                int id = contact.getContact_id();

                String ffname = contact.getContact_firstname();
                String llname = contact.getContact_lastname();
                String nnumber = contact.getContact_number();
                String eemail = contact.getContact_email();

                byte img[]= contact.getImage();

                if(img!=null && img.length!=0) {

                    ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(img);
                    Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);


                    ProfilePosts post7 = new ProfilePosts(id, bitmap, ffname + " " + llname, nnumber, eemail);
                    allPosts.add(post7);
                    myAdapter.notifyDataSetChanged();


                }
                else
                {
                    Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.defaulticon);
                    ProfilePosts post7 = new ProfilePosts(id, largeIcon, ffname + " " + llname, nnumber, eemail);
                    allPosts.add(post7);
                    myAdapter.notifyDataSetChanged();
                }


                Log.d("SHOWCONTACT", id + ffname + llname);


            }

        }
        else
        {

            noContactsTxt.setVisibility(View.VISIBLE);


        }








    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "OnStart called", Toast.LENGTH_LONG).show();
        Log.d("FLOW", "On Start");

        addContactBtn =  (Button) findViewById(R.id.addContactBtn);



        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), AddContact.class);
                startActivityForResult(i, 2);
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode==2)
        {
           // Toast.makeText(getApplicationContext(), "OnActivityResult called", Toast.LENGTH_LONG).show();




            Contact newContact=  myAppDatabase.myDao().getLastInsertedContact();
            String fname= newContact.getContact_firstname();
            String lname= newContact.getContact_lastname();
            String number= newContact.getContact_number();
            String email= newContact.getContact_email();
            int id= newContact.getContact_id();
            byte img[]= newContact.getImage();

            Bitmap bitmap;
            if(img!=null && img.length!=0) {
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(img);
                bitmap = BitmapFactory.decodeStream(arrayInputStream);
            }
            else
            {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.defaulticon);

            }

            String name= fname+" "+ lname;


            if(img!=null && img.length!=0) {

                ProfilePosts post7 = new ProfilePosts(id, bitmap, name, number, email);
                allPosts.add(post7);
                myAdapter.notifyDataSetChanged();
            }
            else
            {

                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.defaulticon);
                ProfilePosts post7 = new ProfilePosts(id, largeIcon, name, number, email);
                allPosts.add(post7);
                myAdapter.notifyDataSetChanged();
            }

            List<String> allnums=  myAppDatabase.myDao().getNumbers();

            for(String num: allnums)
            {
                Log.d("ALL NUMS", num);

            }


            //String image= newContact.getEncoded_image();




            /*
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap decodedbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


            Log.d("SHOWCONTACT", fname+lname);



            Drawable d = new BitmapDrawable(getResources(), decodedbitmap);
            int x=0;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                x=     d.getAlpha();
            }

*/




            //query the database for the most recently inserted contact


           // SQLiteDatabase mydatabase = myHelper.getReadableDatabase();
/*



            MyDatabase myDatabase=new MyDatabase(getApplicationContext(),null, null,  1);
            Cursor cursor= myDatabase.getContact();
         //   Cursor  cursor= mydatabase.rawQuery("SELECT FIRSTNAME FROM CONTACTS", new String[]{});

            if(cursor!=null ) {

                cursor.moveToFirst();

                do {

                    String firstname = cursor.getString(0);
                    //   String lastname= cursor.getString(1);
                    //     String number= cursor.getString(2);
                    //  String email= cursor.getString(3);

                    Log.d("INSERTED", firstname);
                    //    Log.d("INSERTED", lastname);
                    //   Log.d("INSERTED", number);
                    // Log.d("INSERTED", email);


                } while (cursor.moveToNext());


               // cursor.close();

            }



*/


        }



    }




}

