package com.example.smdassign3_q1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "number", foreignKeys = @ForeignKey(entity = Contact.class,
        parentColumns = "contact_id",
        childColumns = "contactid"
        ,onDelete = ForeignKey.CASCADE)
)


public class Number  {


    @PrimaryKey(autoGenerate = true)
    private  int number_id;


    @ColumnInfo(name = "contactnumber")
    private String contactnumber;


    @ColumnInfo(name = "contactid")
    private  int contact_id;


    public int getNumber_id() {
        return number_id;
    }

    public void setNumber_id(int number_id) {
        this.number_id = number_id;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
