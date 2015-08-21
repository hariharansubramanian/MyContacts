package com.example.hari.mycontacts;

import java.util.ArrayList;

/**
 * Created by Nicky on 8/21/2015.
 */
public class ContactList extends ArrayList {
    private static ContactList sContactInstance=null;

    private ContactList(){};

    public static ContactList getContactInstance() {

        if(sContactInstance==null){
            new ContactList();
        }
        return sContactInstance;
    }
}
