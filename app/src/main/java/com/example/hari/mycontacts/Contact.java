package com.example.hari.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nicky on 8/14/2015.
 */
public class Contact implements Serializable {
    private String mName;
    private ArrayList<String> emails = new ArrayList<String>();
    private ArrayList<String> phoneNumbers=new ArrayList<String>();

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }


//Overloaded setEmails to add individual email strings directly into ArrayList
    public void setEmails(String email) {
        this.emails.add(email);
    }
    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    //Overloaded setPhoneNumbers to add individual phone numbers directly into ArrayList
    public void setPhoneNumbers(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
