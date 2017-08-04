package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by Christian on 20.07.2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    //Version of the DATABASE
    public static final int DATABASE_VERSION = 1;
    //NAME of the DATABASE
    public static final String DATABASE_NAME = "habits.db";
    //db object

    //Constructor
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates query of the habit table
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " +
                HabitEntry.TABLE_NAME + "(" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_HABIT_MINUTES + " INTEGER NOT NULL DEFAULT 0, " +
                HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL);";
        //execute the create table query
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DELETE TABLE IF EXISTS " + HabitEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
