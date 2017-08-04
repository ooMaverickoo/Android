package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by Christian on 20.07.2017.
 */

public class HabitContract {

    public static final class HabitEntry implements BaseColumns {
        /**
         * Name of database table for pets
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the pet (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME = "name";

        /**
         * Minutes of the habit.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_MINUTES = "minutes";

        /**
         * Date of the habit.
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE = "date";

    }
}
