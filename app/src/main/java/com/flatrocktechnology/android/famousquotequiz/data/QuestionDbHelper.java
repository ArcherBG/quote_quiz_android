package com.flatrocktechnology.android.famousquotequiz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.flatrocktechnology.android.famousquotequiz.data.QuestionContract.QuestionEntry;

/**
 * Manage a local table tor the question data.
 */
public class QuestionDbHelper extends SQLiteOpenHelper {

    // Important: If you change the database scheme, increment the version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "question.db";

    public QuestionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create a table for the questions.
        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " + QuestionEntry.TABLE_NAME + " (" +
                QuestionEntry.COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionEntry.COLUMN_QUESTION + " TEXT NOT NULL, " +
                QuestionEntry.COLUMN_CORRECT_ANSWER + " TEXT NOT NULL, " +
                QuestionEntry.COLUMN_FIRST_WRONG_ANSWER + " TEXT NOT NULL, " +
                QuestionEntry.COLUMN_SECOND_WRONG_ANSWER  + " TEXT NOT NULL, " +
                QuestionEntry.COLUMN_THIRD_WRONG_ANSWER + " TEXT NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_QUESTION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Deletes current table and creates a new one.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void deleteTable(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionEntry.TABLE_NAME);
    }
}
