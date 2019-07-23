package com.example.diarytest.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.diarytest.data.diaryContract.diaryEntry;

/**
 * This class helps us create and handle the DB. Manages db creation and version management
 */
public class diaryDBhelper extends SQLiteOpenHelper {

    //Constant for db name and version. Increment db version if you change schema
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "diary.db";

    //Constructor
    public diaryDBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //When DB is created
    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL statement to create the diary- table
        String SQL_CREATE_DIARY_TABLE = "CREATE TABLE " + diaryEntry.TABLE_NAME + " ("
                + diaryEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + diaryEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + diaryEntry.COLUMN_CONTENT + " TEXT NOT NULL, "
                + diaryEntry.COLUMN_DATE + " TEXT);";

        //Execute table statement
        db.execSQL(SQL_CREATE_DIARY_TABLE);

    }

    //Method to do when schema of the db is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
