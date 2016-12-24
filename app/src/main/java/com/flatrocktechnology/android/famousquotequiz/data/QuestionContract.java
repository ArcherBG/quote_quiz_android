package com.flatrocktechnology.android.famousquotequiz.data;

/**
 * Defines table and column names for the questions database.
 */
public final class QuestionContract {

    // In order not to instantiate this class.
    private QuestionContract() {
    }

    /* Inner class where we define the columns and their index numbers */
    public static class QuestionEntry {
        // Table name
        public static final String TABLE_NAME = "question";

        // Column with the primary key for the question table.
        public static final String COLUMN_QUESTION_ID = "question_id";
        // Column for the Question to be asked.
        public static final String COLUMN_QUESTION = "question";
        // Column with the correct answer of the question.
        public static final String COLUMN_CORRECT_ANSWER = "correct_answer";

        //Three wrong possible answers for the question.
        public static final String COLUMN_FIRST_WRONG_ANSWER = "first_wrong_answer";
        public static final String COLUMN_SECOND_WRONG_ANSWER = "second_wrong_answer";
        public static final String COLUMN_THIRD_WRONG_ANSWER = "third_wring_answer";


        // Important: If Columns change this must change too!
        // Indexes for Question Table
        public static final int COl_ID = 0;
        public static final int COL_QUESTION = 1;
        public static final int COL_CORR_ANS = 2;
        public static final int COL_FIRST_WRONG_ANS = 3;
        public static final int COL_SECOND_WRONG_ANS = 4;
        public static final int COL_THIRD_WRONG_ANS = 5;
    }
}
