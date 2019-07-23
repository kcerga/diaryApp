package com.example.diarytest.data;

import android.provider.BaseColumns;

public final class diaryContract {

    // Empty constructor to prevent instantiating the contract class.
    private diaryContract() {}


    //Entries for the diary table and columns
    public static abstract class diaryEntry implements BaseColumns {

        public static final String TABLE_NAME = "diaries";
        public static final String COLUMN_ID = "diaryID";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DATE = "entrydate";

    }


}
