package com.example.smdassign3_q1;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {



    @Insert
    public  void insertContact(Contact contact);


    @Query("select * from contacts")
    public List<Contact> getContacts();


    @Query("select * from contacts ORDER BY contact_id DESC LIMIT 1;")
    public  Contact getLastInsertedContact();



    @Insert
    public  void insertNumber(Number number);

    @Query("select contactnumber from Number,CONTACTS where contacts.contact_id=number.contactid")
    public List<String> getNumbers();


    @Query("select DISTINCT contactnumber from Number,CONTACTS where number.contactid=:myid")
    public List<String> getNumbersOfContactById(int myid);

    @Query("select * from CONTACTS where contact_id=:id")
    public Contact getContactById(int id);








}
