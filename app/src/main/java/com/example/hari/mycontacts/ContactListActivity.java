package com.example.hari.mycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    private ArrayList<Contact> contactArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ListView listView = (ListView) findViewById(R.id.contact_list_view);
        contactArrayList = new ArrayList<>();
        listView.setAdapter(new ContactsAdapter(contactArrayList));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {  //Show Action bar on up-scroll, Hide on Down-scroll
            int previousFirstItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > previousFirstItem) {
                    getSupportActionBar().hide();
                } else if (firstVisibleItem < previousFirstItem) {
                    getSupportActionBar().show();

                }
                previousFirstItem = firstVisibleItem;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //On clicking Contact, Send clicked Contact to next Activity
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactArrayList.get(position);
                Intent i = new Intent(ContactListActivity.this, ContactViewActivity.class);
                i.putExtra(ContactViewActivity.EXTRA, contact);
                startActivity(i);
            }
        });

        Contact contact1 = new Contact();
        contact1.setmName("Hari");
        for (int i = 0; i < 30; i++) {
            contactArrayList.add(contact1);
        }
    }

    private class ContactsAdapter extends ArrayAdapter<Contact> {
        ContactsAdapter(ArrayList<Contact> contactArrayList) {
            super(ContactListActivity.this, R.layout.contact_list_row, R.id.contact_row, contactArrayList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);

            Contact contact = getItem(position);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.contact_row);
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
