package com.example.hari.mycontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ListView listView= (ListView) findViewById(R.id.contact_list_view);
        ArrayList<Contact> contacts= new ArrayList<>();
        listView.setAdapter(new ContactsAdapter(contacts));

        Contact contact1=new Contact();
        contact1.setmName("Hari");

    }

    private class ContactsAdapter extends ArrayAdapter<Contact>{
        ContactsAdapter(ArrayList<Contact> contacts){
            super(ContactListActivity.this,R.layout.contact_list_row,R.id.contact_row,contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=super.getView(position, convertView, parent);
            Contact contact=getItem(position);
            TextView nameTextView= (TextView) convertView.findViewById(R.id.contact_row);
            nameTextView.setText(contact.getmName());


            return convertView;
        }
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
