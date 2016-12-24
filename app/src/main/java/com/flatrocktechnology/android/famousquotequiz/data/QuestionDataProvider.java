package com.flatrocktechnology.android.famousquotequiz.data;

import android.app.Activity;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.flatrocktechnology.android.famousquotequiz.data.QuestionContract.QuestionEntry;


/**
 * This class provides ability to read  from database.
 */
public class QuestionDataProvider {
    private static final String TAG = QuestionDataProvider.class.getSimpleName();

    private Activity activity;
    private SQLiteDatabase sqLiteDatabase;

    public QuestionDataProvider(Activity activity) {
        this.activity = activity;
        QuestionDbHelper dbHelper = new QuestionDbHelper(activity);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    /**
     * Read data from DB by selecting  ro.
     */
    public Cursor findRow(int rowId) {
        if (!sqLiteDatabase.isOpen()) {
            QuestionDbHelper dbHelper = new QuestionDbHelper(activity);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            return null;
        } else {
            // Use a cursor to query the database.
            Cursor cursor = sqLiteDatabase.query(
                    QuestionEntry.TABLE_NAME,  // table name
                    null, // leaving "columns" null just returns all the columns.
                    QuestionEntry.COLUMN_QUESTION_ID + " =?",  // cols for "where" clause
                    new String[]{Integer.toString(rowId)}, // values for "where" clause
                    null, // columns to group by
                    null, // columns to filter by row groups
                    null  // sort order
            );
            return cursor;
        }
    }

    public int getCount() {
        if (!sqLiteDatabase.isOpen()) {
            QuestionDbHelper dbHelper = new QuestionDbHelper(activity);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            return 0;
        } else {
            long rowCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, QuestionEntry.TABLE_NAME);
            return (int) rowCount;
        }
    }

    public void closeConnectionToDb() {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

}
