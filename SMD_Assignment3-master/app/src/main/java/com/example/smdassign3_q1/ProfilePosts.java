package com.example.smdassign3_q1;

import android.graphics.Bitmap;

public class ProfilePosts {
    private Bitmap image;
    String contactName;
    String contactNumber;
    String contactEmail;
    int contactId;

    public ProfilePosts(int id, Bitmap image, String cName, String cNumber, String cEmail) {
        this.image = image;
        this.contactName = cName;
        this.contactNumber = cNumber;
        this.contactEmail = cEmail;
        this.contactId=id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}