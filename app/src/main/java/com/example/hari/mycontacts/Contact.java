package com.example.hari.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nicky on 8/14/2015.
 */
public class Contact implements Serializable {
    private String mName;
    private ArrayList<String> emails;
    private ArrayList<String> numbers;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
