package com.example.diarytest;

import android.content.Context;
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
import java.util.ArrayList;
import static com.example.diarytest.data.diaryContract.diaryEntry.COLUMN_ID;
import static com.example.diarytest.data.diaryContract.diaryEntry.COLUMN_TITLE;

public class MainActivity extends AppCompatActivity {

    //A helper to access the database
    private diaryDBhelper myDBhelper = new diaryDBhelper(this);


    //Default setting to sort diaries
    private String orderDiaries = COLUMN_ID + " DESC";


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
                COLUMN_ID,
                COLUMN_TITLE,
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
                orderDiaries );
        try {


            //Get the index of each column
            int IDindex = cursor.getColumnIndex(COLUMN_ID);
            int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
            int contextIndex = cursor.getColumnIndex(diaryEntry.COLUMN_CONTENT);
            int dateIndex = cursor.getColumnIndex(diaryEntry.COLUMN_DATE);

            ArrayList<Entry> diaries = new ArrayList<Entry>();

            //Iterate through all the rows
            while (cursor.moveToNext()) {

                //Extract the speficic information in that column with the index
                int integerID = cursor.getInt(IDindex);
                //Convert ID to string for now
                String currentID = new Integer(integerID).toString();
                String currentTitle = cursor.getString(titleIndex);
                String currentDate = cursor.getString(dateIndex);


                diaries.add(new Entry(currentID, currentTitle, currentDate));

            }
            entryAdapter diaryentries = new entryAdapter(this, diaries);

            //ListView that will be display on the list
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(diaryentries);


        } finally {
            // Close the cursor
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


    //Menu items to sort how the entries will be displayed on the screen
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.newFirst:
                //Sort by newest entry
                orderDiaries = COLUMN_ID + " DESC";
                displayInformation();
                return true;

            case R.id.oldFirst:
                //Sort by oldest entry first
                orderDiaries = COLUMN_ID + " ASC";
                displayInformation();
                return true;

            case R.id.alphabetical:
                //Sort alphabetically
                orderDiaries = COLUMN_TITLE + " ASC";
                displayInformation();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
