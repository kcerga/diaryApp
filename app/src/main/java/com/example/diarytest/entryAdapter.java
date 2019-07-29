package com.example.diarytest;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom arrayadapter, so we can show multiple views on the listview
 * Inherits the methods and variables from arrayadapter
 */

public class entryAdapter extends ArrayAdapter<Entry> {

    //Constructor
    public entryAdapter(Activity context, ArrayList<Entry> number) {
        super(context, 0, number);
    }

    //Provides a view for an adapterview
    // Overrides the getView method from the superclass.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.diary_items, parent, false);
        }


            // Object located at this position in the list
            Entry currentEntry = getItem(position);

            // Find the TextView in the diary_items.xml layout with the diary title
            TextView titleTextview = (TextView) listItemView.findViewById(R.id.diary_title);
            // Gets the title for this text, and sets it on the TextView
            titleTextview.setText(currentEntry.getDiaryTitle());

            //Find textview for the diary ID
            TextView IDTextview = (TextView) listItemView.findViewById(R.id.diary_ID);
            // Set ID to the textview
            IDTextview.setText(currentEntry.getDiaryID());

            //Hide ID for now
            IDTextview.setVisibility(View.GONE);


            // Same thing for the diary date
            TextView dateTextview = (TextView) listItemView.findViewById(R.id.diary_date);

            //Gets the date's position and sets it in the textview
            dateTextview.setText(currentEntry.getDiaryDate());



        // Return the whole list item layout (containing 2 TextViews & 1 img )
        // so that it can be shown in the ListView
        return listItemView;

    }

}