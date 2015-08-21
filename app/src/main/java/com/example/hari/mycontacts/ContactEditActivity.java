package com.example.hari.mycontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactEditActivity extends AppCompatActivity {
    public static final String EXTRA = "CEA_EXTRA";
    private EditText editName;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        contact = (Contact) getIntent().getSerializableExtra(EXTRA);

        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_edit_toolbar);
        toolbar.setTitle(getResources().getString(R.string.edit_contact));
        toolbar.setNavigationIcon(R.drawable.ic_done);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ToolbarClick:", "Navigation icon Clicked");

                contact.setmName(editName.getText().toString());
                contact.setPhoneNumbers(getSectionItems(R.id.phonenumber_sectionLinearlayout));
                contact.setEmails(getSectionItems(R.id.email_sectionLinearlayout));

                finish();

            }
        });

        editName = (EditText) findViewById(R.id.edit_name);
        editName.setText(contact.getmName());

        addToSection(R.id.phonenumber_sectionLinearlayout, contact.getPhoneNumbers());
        addToSection(R.id.email_sectionLinearlayout, contact.getEmails());
        TextView addNewPhoneNumber = (TextView) findViewById(R.id.add_new_phoneNumber);

        addNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADD:", "PhoneNumber");
                addNewToSection(R.id.phonenumber_sectionLinearlayout, "");
            }
        });
        TextView addNewEmail = (TextView) findViewById(R.id.add_new_email);
        addNewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADD:", "EMAIL");
                addNewToSection(R.id.email_sectionLinearlayout, "");
            }
        });


    }

    //To ArrayList Strings of items under phonenumber section or email section of linearlayout
    private ArrayList<String> getSectionItems(int sectionID) {

        ArrayList<String> childItems = new ArrayList<>();
        LinearLayout section = (LinearLayout) findViewById(sectionID);

        for (int i = 0; i < section.getChildCount(); i++) {
            EditText et= (EditText) section.getChildAt(i);
            childItems.add(et.getText().toString());

            Log.d("Adding to ArrayList:",childItems.get(i));


        }
        return childItems;
    }

    //create new edit text field,sets layoutparam and sets text = String value
    private void addNewToSection(int sectionID, String value) {
        LinearLayout section = (LinearLayout) findViewById(sectionID);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lp);
        et.setText(value);
        section.addView(et);
    }

    //create new edit text fields,sets layoutparam and text for each value in ArrayList<> email or ArrayList<> phoennumbers
    private void addToSection(int sectionID, ArrayList<String> values) {
        LinearLayout section = (LinearLayout) findViewById(sectionID);
        for (int i = 0; i < values.size(); i++) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(values.get(i));
            section.addView(et);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_edit, menu);
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
