package com.flatrocktechnology.android.famousquotequiz.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.flatrocktechnology.android.famousquotequiz.data.QuestionContract.QuestionEntry;

import java.util.Vector;

/**
 * Ths class provides tha data to be inserted in the local database.
 */
public class InitialDatabasePopulation {
    private static final String TAG = InitialDatabasePopulation.class.getSimpleName();

    public void createQuestionValues(Activity activity){

        // Open Db for writing
        QuestionDbHelper dbHelper = new QuestionDbHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

       // Get Questions to save in DB.
        Vector<ContentValues> questionsVector = insertAllQuestionsToVector();

        // Insert into database
        for(int i=0; i<questionsVector.size(); i++){
            long rowId = db.insert(QuestionEntry.TABLE_NAME, null, questionsVector.get(i));
        }

        // Close the database
        db.close();
    }

     private Vector<ContentValues> insertAllQuestionsToVector(){
         Vector<ContentValues> contentValuesVector = new Vector<>();
         ContentValues questionValues;

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "It it is on the internet the is must be true");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "The Internet said it");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Ivan");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Asen");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Misho");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "Victory was near. But the power of the Ring could not be undone");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Arwen");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Elrond");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Galadriel");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Gandalf");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "Frodo fîr. Ae athradon i hir, tur gwaith nîin beriatha hon");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Haldir");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Elrond");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Aragorn");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Galadriel");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "Laugh it up, fuzzball.");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Han Solo");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Ivan");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Asen");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Misho");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "To err is human, to forgive…");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Davine ");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "You");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Thomas");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Oscar Wilde");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "Who said “Give me a museum and I’ll fill it!");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Picasso");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Dylan Thomas");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Oscar Wilde");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Divine");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "A forgone conclusion” is a commonly used phrase which was originally penned by which author");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "William Shakespeare ");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Richard III");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Stand");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Divine");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, " The reports of my death are greatly exaggerated? ");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Mark Twain");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Diana, Princess Of Wales ");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Eric Cantona");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Margaret Thatcher ");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "Eight that are here yet nine there were set out from Rivendell. Tell me where is Gandalf? For I much desire to speak with him.");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Celeborn");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Elrond");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Galadriel");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Theoden");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "I will find no rest here. I heard her voice inside my head. She spoke of my father and the fall of Gondor. She said to me even now there is hope left. But I cannot see it. It is long since we had any hope");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Legolas");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Boromir");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Frodo");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Sam");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "That was no lady that was my wife?");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Groucho Marx");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Charlie Chaplin ");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Margaret Thatcher ");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Eric Cantona ");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, ". There will be no whitewash at the White House  ");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Richard Nixon");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Charlie Chaplin ");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Margaret Thatcher ");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Eric Cantona ");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "It");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "The ");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Charlie Chaplin ");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Margaret Thatcher ");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Eric Cantona ");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "No woman in my time will ever be Prime Minister?");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Margaret Thatcher ");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Elizabeth");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Martina");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Eric Cantona ");
         contentValuesVector.add(questionValues);

         // Create a map of values to be put in a single row.
         questionValues = new ContentValues();
         questionValues.put(QuestionEntry.COLUMN_QUESTION, "It suddenly struck me that that tiny pea, pretty and blue, was the earth. I put up my thumb and shut one eye, and my thumb blotted out the planet earth.");
         questionValues.put(QuestionEntry.COLUMN_CORRECT_ANSWER, "Neil Armstrong");
         questionValues.put(QuestionEntry.COLUMN_FIRST_WRONG_ANSWER, "Charlie Chaplin ");
         questionValues.put(QuestionEntry.COLUMN_SECOND_WRONG_ANSWER, "Margaret Thatcher ");
         questionValues.put(QuestionEntry.COLUMN_THIRD_WRONG_ANSWER, "Eric Cantona ");
         contentValuesVector.add(questionValues);

         return contentValuesVector;
     }


}
