package com.example.hari.mycontacts;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactViewActivity extends AppCompatActivity {
    public static final String EXTRA = "CVA_Contact";
    Palette palette;
    private int relativeColor;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        //get screen height and width
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;

        //now calculating height for RelativeLayout containing Contact image and name with 16:9 ratio
        RelativeLayout headerSection = (RelativeLayout) findViewById(R.id.header_section);
        //takes argument new parent layout type, in order to reference itself
        headerSection.setLayoutParams(new LinearLayout.LayoutParams(width, (int) ((width * (9.0 / 16.0)))));

        //Setting Toolbar as Action bar. Doing this because Toolbar can be fully customized.
        android.support.v7.widget.Toolbar toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.contact_view_toolbar);

        //on Toolbar Menu Item Click
        toolBar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.contact_view_edit) {
                    Log.d("EDIT", "Clicked on Edit icon");
                    return true;
                }


                return false;
            }
        });
        // inflate menu for custom look instead of setSupportActionBar(toolbar);
        toolBar.inflateMenu(R.menu.menu_contact_view);

        Contact contact = (Contact) getIntent().getSerializableExtra(EXTRA);
        TextView contactName = (TextView) findViewById(R.id.contact_name);
        contactName.setText(contact.getmName());

        //ListView for Emails and Numbers Using BaseAdapter
        ListView listView = (ListView) findViewById(R.id.list_view_fields);
        listView.setAdapter(new FieldsAdapter(contact.getPhoneNumbers(), contact.getEmails()));

        //Using Palette to get a suitable int color based on given Bitmap, Dynamic Color
        //passing image resource to be decoded into bitmap colors
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        //generate a Pallete by giving in the decoded bitmap to get a relative color
        palette = Palette.generate(bitmap);
        //Using palette and VibrantSwatch to generate a relative rgb int color
        //TODO
        //must do a null check on palette because it does not get a vibrantswatch sometimes
        if (palette.getDarkVibrantSwatch()!=null) {
            relativeColor = palette.getDarkVibrantSwatch().getRgb();
        }

    }

    //Custom List View Adapter extending BaseAdapter and implement the 4 default functions getCount(),getView(),getItemId(),getItem()
    private class FieldsAdapter extends BaseAdapter {

        private ArrayList<String> emails;
        private ArrayList<String> phoneNumbers;

        FieldsAdapter(ArrayList<String> phoneNumbers, ArrayList<String> emails) {
            this.phoneNumbers = phoneNumbers;
            this.emails = emails;

        }

        public boolean isPhoneNumber(int position) {
            if (position < phoneNumbers.size()) {
                return true;
            } else {
                return false;

            }
        }

        // if First Phone number or First Email, used to add drawable Call or email icon in getView
        private boolean isFirstItem(int position) {
            if (position == 0 || position == phoneNumbers.size()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int getCount() {       //tells BaseAdapter how many rows to populate
            return emails.size() + phoneNumbers.size();
        }

        //getView does not have a super() like ArrayAdapter, must define our own function from scratch
        //so we inflate our own layout into our view using getLayoutInflater().inflate(layout,parent,false)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = ContactViewActivity.this.getLayoutInflater().inflate(R.layout.contact_view_email_num_row, parent, false);
            }
            String items = (String) getItem(position);

            TextView textViewEmailAndNum = (TextView) convertView.findViewById(R.id.contact_view_email_num_textview);
            textViewEmailAndNum.setText(items);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageview_emailOrNum);
            if (isFirstItem(position)) {


                if (isPhoneNumber(position)) {
                    iv.setImageResource(R.drawable.ic_call);
                } else {
                    iv.setImageResource(R.drawable.ic_email);
                }
            }
            if (palette.getDarkVibrantSwatch() != null) {
                iv.setColorFilter(relativeColor);
            } else {
                iv.setColorFilter(getResources().getColor(R.color.primary));
            }
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        //Note:ArrayList<> Emails is after the phonenumbers ArrayList
        //So if position selected is greater than phonenumbers.size(), must take element from emails ArrayList
        public Object getItem(int position) {
            if (isPhoneNumber(position)) {
                return phoneNumbers.get(position);
            } else {
                return emails.get(position - phoneNumbers.size());
            }

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
