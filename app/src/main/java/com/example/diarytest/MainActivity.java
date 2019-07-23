package com.example.diarytest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;

import com.example.diarytest.data.diaryContract.diaryEntry;
import com.example.diarytest.data.diaryDBhelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //A helper to access the database
    private diaryDBhelper myDBhelper = new diaryDBhelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating action button to add a new diary
        FloatingActionButton fab = findViewById(R.id.fab);
        //Onclick listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //New intent to enter entryActivity where new diaries are added
                    Intent intent = new Intent(MainActivity.this, entryActivity.class);
                    startActivity(intent);
                }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Call display information method
        displayInformation();
    }


    //Helper method to see that the rows we insert are actually going in the database. Call this method to see if it works
    private void displayInformation() {

        //Create or open DB and read from it
        SQLiteDatabase dbRead = myDBhelper.getReadableDatabase();

        //A string that contains all the columns we want to read from DB
        String [] project = {
                diaryEntry.COLUMN_ID,
                diaryEntry.COLUMN_TITLE,
                diaryEntry.COLUMN_CONTENT,
                diaryEntry.COLUMN_DATE
        };

        //Cursor that will contain the rows that we will read. Takes the 'project' as an argument. Null- statements are order by etc
        Cursor cursor = dbRead.query(
                diaryEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null );
        try {

            //Find the textview do display information, and get the count of the current rows in DB table
            TextView displayView = (TextView) findViewById(R.id.list);
            displayView.setText("Current rows: " +  cursor.getCount());

            //Get the index of each column
            int IDindex = cursor.getColumnIndex(diaryEntry.COLUMN_ID);
            int titleIndex = cursor.getColumnIndex(diaryEntry.COLUMN_TITLE);
            int contextIndex = cursor.getColumnIndex(diaryEntry.COLUMN_CONTENT);
            int dateIndex = cursor.getColumnIndex(diaryEntry.COLUMN_DATE);

            //Iterate through all the rows
            while (cursor.moveToNext()) {

                //Using the index we took earlier, we can extract the speficic information in that column
                int currentID = cursor.getInt(IDindex);
                String currentTitle = cursor.getString(titleIndex);
                String currentContent = cursor.getString(contextIndex);
                String currentDate = cursor.getString(dateIndex);

                //Append all the info to the displayView
                displayView.append("\n" + currentTitle + "\n" + currentDate + "\n" + currentContent + "\n" );


            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_main.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_diary:
                //Do nothing yet
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
/**
 *
 * FOR VIEW RECYCLLING
 *
 * ArrayList<Entry> diaries = new ArrayList<Entry>();

 entryAdapter diaryentries = new entryAdapter(this, diaries);

 //ListView that will be display on the @list
 ListView listView = (ListView) findViewById(R.id.list);

 diaries.add(new Entry(currentID, currentTitle, currentDate));
 //Makes the created listview to use the adapter, so the list will display the list items.
 listView.setAdapter(diaryentries);**/