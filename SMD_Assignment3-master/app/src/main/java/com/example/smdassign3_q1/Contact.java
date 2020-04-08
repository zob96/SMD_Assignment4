package com.example.smdassign3_q1;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {

    public static final String TABLE_NAME= "contacts";


    public static final String COLUMN_ID= "id";
    public static final String COLUMN_FIRSTNAME= "firstname";
    public static final String COLUMN_LASTNAME= "lastname";
    public static final String COLUMN_NUMBER= "numberid";
    public static final String COLUMN_EMAIL= "email";



    @PrimaryKey(autoGenerate = true)
    private  int contact_id;

    @ColumnInfo(name = "firstname")
    private String contact_firstname;

    @ColumnInfo(name = "lastname")
    private String contact_lastname;

    @ColumnInfo(name = "number")
    private String contact_number;

    @ColumnInfo(name = "email")
    private String contact_email;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;






    public Contact()
    {

    }



    public static final String CREATE_TABLE= "CREATE TABLE "+ TABLE_NAME + "("
                                                            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                            +COLUMN_FIRSTNAME+ " TEXT,"
                                                            +COLUMN_LASTNAME+ " TEXT,"
                                                            +COLUMN_NUMBER+ " INTEGER,"
                                                            +COLUMN_EMAIL+ " TEXT"
                                                            + ")";

    public Contact(int id, String first, String last, String num, String email)
    {
        this.contact_id=id;
        this.contact_firstname= first;
        this.contact_lastname= last;
        this.contact_number= num;
        this.contact_email= email;

    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_firstname() {
        return contact_firstname;
    }

    public void setContact_firstname(String contact_firstname) {
        this.contact_firstname = contact_firstname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getContact_lastname() {
        return contact_lastname;
    }

    public void setContact_lastname(String contact_lastname) {
        this.contact_lastname = contact_lastname;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }


    //   +" FOREIGN KEY ("+COLUMN_NUMBER_ID+") REFERENCES "+

                                                           //


}
