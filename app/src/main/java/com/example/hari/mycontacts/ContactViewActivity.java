package com.example.hari.mycontacts;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class ContactViewActivity extends AppCompatActivity {
    public static final String EXTRA = "CVA_Contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        //get screen height and width
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int height = point.x;
        int width = point.y;

        //now calculating height for 16:9 ratio for RelativeLayout containing Contact image and name
        RelativeLayout headerSection = (RelativeLayout) findViewById(R.id.header_section);
        headerSection.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) ((width * (9.0 / 16.0)))));

        //Setting Toolbar as Action bar. Doing this because Toolbar can be fully customized.
        android.support.v7.widget.Toolbar toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.contact_view_toolbar);
        setSupportActionBar(toolBar);

        toolBar.inflateMenu(R.menu.menu_main);

        Contact contact = (Contact) getIntent().getSerializableExtra(EXTRA);
        TextView contactName = (TextView) findViewById(R.id.contact_name);
        contactName.setText(contact.getmName());

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
