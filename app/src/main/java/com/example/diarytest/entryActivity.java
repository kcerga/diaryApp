package com.example.diarytest;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.diarytest.data.diaryContract.diaryEntry;
import com.example.diarytest.data.diaryDBhelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class entryActivity extends AppCompatActivity {

    //Helper that lets uss access the DB
    private diaryDBhelper myDBhelper;

    //Variables for the text objects
    private EditText titleEdit;
    private EditText contextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Instantiate subclass of SQLiteHelper, and pass context (current activity)
        myDBhelper = new diaryDBhelper(this);

        //Find all the editor views that we need

        titleEdit = (EditText) findViewById(R.id.diary_title);
        contextEdit = (EditText) findViewById(R.id.diary_content);

    }


    //Method to save the diary entry in to the database
    private void saveDiary() {

        //Get the text from the EditTexts and turn them to strings, set them as the user inputs
        //Trim to eliminate useless whitespace
        String userTitle = titleEdit.getText().toString().trim();
        String userContext =  contextEdit.getText().toString().trim();

        //Get current date and turn it to a string
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        //Write into the db
        SQLiteDatabase dbWrite = myDBhelper.getWritableDatabase();

        //ContentValues where key is the column name and value is the text
        ContentValues values = new ContentValues();
        values.put(diaryEntry.COLUMN_TITLE, userTitle);
        values.put(diaryEntry.COLUMN_CONTENT, userContext);
        values.put(diaryEntry.COLUMN_DATE, currentDate);


        //Insert a new row. Takes the table name, columnhack that is usually null and the values created
        long newRow = dbWrite.insert(diaryEntry.TABLE_NAME, null, values);


        //Toast- that will come after, depending if the insert was succesfull or not.
        // -1 is the value of the newRow if there was an error
        if (newRow == -1) {
            Toast.makeText(this, "There was an error", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Diary entry added!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_entry.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:

                //Save the diary entry to DB
                saveDiary();

                //Exit and go back to mainActivity
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
