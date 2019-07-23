package com.example.diarytest;

/**
 * Class that includes the ID, title and date of each diary entry
 */

public class Entry {

    private String diaryTitle;
    private int diaryID;
    private String diaryDate;

    //Constructor that takes the ID, title and date of the diary entry
    public Entry(int diaryID, String diaryTitle, String diaryDate) {
        this.diaryID = diaryID;
        this.diaryTitle = diaryTitle;
        this.diaryDate = diaryDate;

    }

    //Return ID
    public int getDiaryID() { return diaryID; }

    //Return title
    public String getDiaryTitle() { return diaryTitle; }

    //Return diary date
    public String getDiaryDate() { return diaryDate; }


}
