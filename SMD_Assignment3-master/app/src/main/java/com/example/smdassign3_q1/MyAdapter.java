package com.example.smdassign3_q1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String [] data;
    private ArrayList<ProfilePosts>  postData;
    Bitmap myimg;
    String mycontactname;
    String mycontactnumber;
    String mycontactemail;
    Context context;
    int mycontactid;


    public MyAdapter(ArrayList<ProfilePosts> postData, Context context) {


        this.postData = postData;
        this.context= context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myrow, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //String title = data[position];
        myimg = postData.get(position).getImage();
        mycontactname = postData.get(position).getContactName();
        mycontactnumber = postData.get(position).getContactNumber();
        mycontactemail = postData.get(position).getContactEmail();
        mycontactid= postData.get(position).getContactId();




       // holder.imageView.setImageAlpha(myimg);
       // holder.imageView.setImageDrawable(R.drawable.babusar);
        holder.imageView.setImageBitmap(myimg);
        holder.name.setText(mycontactname);
//
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                Intent i= new Intent(context, ContactDetails.class);
//                i.putExtra("image", postData);
//                i.putExtra("name", mycontactname);
//                i.putExtra("email", mycontactemail);
//                i.putExtra("number", mycontactnumber);
//
//                view.getContext().startActivity(i);
//            }
//        });
//



       // holder.textView.setText(title);

    }

    @Override
    public int getItemCount() {
       // return data.length;
        return postData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        //TextView textView;
        TextView name;
        RelativeLayout relativeLayout;
        //private final Context context;
        public MyViewHolder (View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView4);
            name = itemView.findViewById(R.id.contactName);
            relativeLayout= itemView.findViewById(R.id.relative);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String cname =postData.get(pos).getContactName();
                    String cnumber= postData.get(pos).getContactNumber();
                    String cemail= postData.get(pos).getContactEmail();
                    Bitmap cimg= postData.get(pos).getImage();
                    int cid= postData.get(pos).getContactId();

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    cimg.compress(Bitmap.CompressFormat.PNG, 100, out);
                    Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    decoded.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();



                    Intent i= new Intent(context, ContactDetails.class);
                    i.putExtra("id", cid);
                    i.putExtra("name", cname);
                    i.putExtra("email", cemail);
                    i.putExtra("number", cnumber);
                 //   i.putExtra("image", byteArray);



                 //   i.putExtra("image", decoded);






                    view.getContext().startActivity(i);

                }
            });
            //context = itemView.getContext();

        }





    }

}
