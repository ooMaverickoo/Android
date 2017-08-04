package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;


public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creates the habitDbHelper
        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Insert data into the database habits
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Running");
        values.put(HabitEntry.COLUMN_HABIT_MINUTES, 60);
        values.put(HabitEntry.COLUMN_HABIT_DATE, "20.07.2017");
        insertHabit(values);

        values.put(HabitEntry.COLUMN_HABIT_NAME, "Horse Riding");
        values.put(HabitEntry.COLUMN_HABIT_MINUTES, 120);
        values.put(HabitEntry.COLUMN_HABIT_DATE, "18.07.2017");
        insertHabit(values);


        //Create cursor about function
        Cursor cursor = queryAllData();

        try {


            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int minutesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_MINUTES);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentMinutes = cursor.getInt(minutesColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView

                String data = ("\n" + currentID + " - " +
                        currentName + " - " +
                        currentMinutes + " - " +
                        currentDate);

                Log.v("Database Data: ", data);

            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    public Cursor queryAllData() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_MINUTES,
                HabitEntry.COLUMN_HABIT_DATE};

        //Cursor
        return db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }


    public void insertHabit(ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.insert(HabitEntry.TABLE_NAME, null, values);
    }

}
