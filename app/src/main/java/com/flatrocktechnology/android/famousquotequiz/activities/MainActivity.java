package com.flatrocktechnology.android.famousquotequiz.activities;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.flatrocktechnology.android.famousquotequiz.R;
import com.flatrocktechnology.android.famousquotequiz.data.InitialDatabasePopulation;
import com.flatrocktechnology.android.famousquotequiz.data.QuestionContract.QuestionEntry;
import com.flatrocktechnology.android.famousquotequiz.data.QuestionDataProvider;
import com.flatrocktechnology.android.famousquotequiz.fragments.BinaryChoiceModeFragment;
import com.flatrocktechnology.android.famousquotequiz.fragments.BinaryChoiceModeFragment.OnYesNoAnswerClickListener;
import com.flatrocktechnology.android.famousquotequiz.fragments.DialogAnswerFragment;
import com.flatrocktechnology.android.famousquotequiz.fragments.DialogAnswerFragment.OnDialogAnswerClickListener;
import com.flatrocktechnology.android.famousquotequiz.fragments.MultipleChoiceModeFragment;
import com.flatrocktechnology.android.famousquotequiz.fragments.MultipleChoiceModeFragment.OnAnswerClickListener;
import com.flatrocktechnology.android.famousquotequiz.fragments.PlayAgainFragment;
import com.flatrocktechnology.android.famousquotequiz.fragments.PlayAgainFragment.OnPlayAgainClickListener;
import com.flatrocktechnology.android.famousquotequiz.fragments.SettingsFragment;
import com.flatrocktechnology.android.famousquotequiz.model.QuizQuestionDao;
import com.flatrocktechnology.android.famousquotequiz.sessionmanager.SessionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements OnAnswerClickListener,
        OnDialogAnswerClickListener, OnPlayAgainClickListener,
        SettingsFragment.OnSettingsClickListener, OnYesNoAnswerClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String QUESTIONS_BUNDLE_KEY = "questions_bundle";
    public static final String PLAY_AGAIN_BUNDLE_KEY = "playAgainBundleKey";
    private static final String DIALOG_ANSWER_BUNDLE_KEY = "dialog_bundle_key";

    private TabLayout tabBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialLoadActivity();
    }

    private void initialLoadActivity() {
        Log.d(TAG, "initialLoadActivity called");

        // Load Tab Layout
        tabBar = (TabLayout) findViewById(R.id.tabBar);

        tabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                // Position - 0 is the main screen Tab, and 1 is Setting screen
                if (tab.getPosition() == 0) {

                    Fragment currentVisibleFragment = getFragmentManager().findFragmentById(R.id.flActivityMain);
                    if (currentVisibleFragment instanceof MultipleChoiceModeFragment ||
                            currentVisibleFragment instanceof BinaryChoiceModeFragment) {
                        // do nothing
                    } else {
                        //Remove Settings screen
                        onBackPressed();
                    }
                } else {

                    SettingsFragment settingsFragment = new SettingsFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction
                            .replace(R.id.flActivityMain, settingsFragment, "SettingsFragment")
                            .addToBackStack("SettingsFragment")
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        SessionManager sessionManager = SessionManager.getInstance(getApplicationContext());

        // If the user is visiting the app for the first time save the question to local Db.
        if (sessionManager.getIsUserFirstTime()) {
            InitialDatabasePopulation dbPopulation = new InitialDatabasePopulation();
            dbPopulation.createQuestionValues(this);
            sessionManager.setIsUserFirstTime(true);
        }

        // Load the quiz in the appropriate mode
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putSerializable(QUESTIONS_BUNDLE_KEY, (Serializable) loadQuestionsFromDb());

        if (sessionManager.isBinaryModeQuizEnabled()) {

            BinaryChoiceModeFragment binaryChoiceModeFragment = new BinaryChoiceModeFragment();
            binaryChoiceModeFragment.setArguments(args);
            fragmentTransaction
                    .add(R.id.flActivityMain, binaryChoiceModeFragment, "BinaryChoiceModeFragment")
                    .commit();
        } else {

            MultipleChoiceModeFragment multipleChoiceModeFragment = new MultipleChoiceModeFragment();
            multipleChoiceModeFragment.setArguments(args);
            fragmentTransaction
                    .add(R.id.flActivityMain, multipleChoiceModeFragment, "MultipleChoiceModeFragment")
                    .commit();

        }
    }

    private void secondaryLoadActivity() {
        Log.d(TAG, "secondaryLoadActivity called");

        // Remove all fragments
       // getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentManager fm = getFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }

        // Make the Tab bar visible again
        tabBar.setVisibility(View.VISIBLE);

        SessionManager sessionManager = SessionManager.getInstance(getApplicationContext());
        Bundle args = new Bundle();
        args.putSerializable(QUESTIONS_BUNDLE_KEY, (Serializable) loadQuestionsFromDb());

//        // Load the quiz in the appropriate mode
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (sessionManager.isBinaryModeQuizEnabled()) {

            BinaryChoiceModeFragment binaryChoiceModeFragment = new BinaryChoiceModeFragment();
            binaryChoiceModeFragment.setArguments(args);
            fragmentTransaction
                    .replace(R.id.flActivityMain, binaryChoiceModeFragment, "BinaryChoiceModeFragment")
                    .commit();
        } else {

            MultipleChoiceModeFragment multipleChoiceModeFragment = new MultipleChoiceModeFragment();
            multipleChoiceModeFragment.setArguments(args);
            fragmentTransaction
                    .replace(R.id.flActivityMain, multipleChoiceModeFragment, "MultipleChoiceModeFragment")
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {

            Fragment currentVisibleFragment = getFragmentManager().findFragmentById(R.id.flActivityMain);

//            // If user clicks back button when PlayAgainFragment is showing exit the app
//            if (currentVisibleFragment instanceof PlayAgainFragment) {
//                Log.d(TAG, "onBackPressed play again sfragment is showing");
//                this.finish();
//            }

            // if user pressed back button highlight the other tab
            if (currentVisibleFragment instanceof SettingsFragment) {
                // Select the first tab manually
                TabLayout.Tab newTab = tabBar.getTabAt(0);
                newTab.select();
                getFragmentManager().popBackStack();
            } else {
                // Close  the application
                finish();
            }
        }

    }

    /* Loads from Db 10 not repeating questions and possible answers */
    private List<QuizQuestionDao> loadQuestionsFromDb() {

        ArrayList<QuizQuestionDao> questionsList = new ArrayList<>();
        QuestionDataProvider dataProvider = new QuestionDataProvider(this);
        int totalCount = dataProvider.getCount();

        //Generate 10 non repeating numbers
        int[] rowIdToGet = new int[10];
        for (int i = 0; i < rowIdToGet.length; i++) {
            while (true) {
                int rand = generateRandNum(totalCount);
                if (!isNumContainsInArray(rand, rowIdToGet)) {
                    rowIdToGet[i] = rand;
                    break;
                }
            }
        }

        //Get 10 Questions from Db
        for (int i = 0; i < rowIdToGet.length; i++) {
            Cursor cursor = dataProvider.findRow(rowIdToGet[i]);
            if (cursor != null && cursor.moveToFirst()) {
                // Get fields from Db.
                String question = cursor.getString(QuestionEntry.COL_QUESTION);
                String corrAnswer = cursor.getString(QuestionEntry.COL_CORR_ANS);
                String firstWrongAnswer = cursor.getString(QuestionEntry.COL_FIRST_WRONG_ANS);
                String secondWrongAnswer = cursor.getString(QuestionEntry.COL_SECOND_WRONG_ANS);
                String thirdWrongAnswer = cursor.getString(QuestionEntry.COL_THIRD_WRONG_ANS);

                // Put in dao object
                QuizQuestionDao dao = new QuizQuestionDao();
                dao.setQuestion(question);
                dao.setCorrAnswer(corrAnswer);
                dao.setFirstWrongAnswer(firstWrongAnswer);
                dao.setSecondWrongAnswer(secondWrongAnswer);
                dao.setThirdWrongAnswer(thirdWrongAnswer);

                // Add in ArrayList
                questionsList.add(dao);
            }
        }

        // Close Db
        dataProvider.closeConnectionToDb();

        return questionsList;
    }

    private int generateRandNum(int max) {
        Random random = new Random();
        int rand = random.nextInt(max - 1) + 1; // between 1 and max
        return rand;
    }

    private boolean isNumContainsInArray(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (num == array[i]) {
                return true;
            }
        }
        return false;
    }

    /* Callback methods are listed below*/

    /* When user finish the quiz load the play again screen. */
    @Override
    public void finishedQuiz(int correctAnswers) {
        // Remove tab bar when showing Play again Screen
        tabBar.setVisibility(View.GONE);

        PlayAgainFragment playAgainFragment = new PlayAgainFragment();
        Bundle args = new Bundle();
        args.putInt(PLAY_AGAIN_BUNDLE_KEY, correctAnswers);
        playAgainFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.flActivityMain, playAgainFragment, "PlayAgainFragment")
                .addToBackStack("PlayAgainFragment")
                .commit();
    }

    @Override
    public void showDialog(String dialogTitle) {
        DialogAnswerFragment dialogAnswerFragment = new DialogAnswerFragment();
        Bundle args = new Bundle();
        args.putString(DIALOG_ANSWER_BUNDLE_KEY, dialogTitle);
        dialogAnswerFragment.setArguments(args);
        dialogAnswerFragment.show(getFragmentManager(), "DialogTag");
    }


    @Override
    public void onDismissButtonClick() {
        // Answer dialog is now closed so load the next question depending on what fragment is showing
        MultipleChoiceModeFragment multipleChoiceModeFragment = (MultipleChoiceModeFragment) getFragmentManager()
                .findFragmentByTag("MultipleChoiceModeFragment");
        if (multipleChoiceModeFragment != null) {
            multipleChoiceModeFragment.loadNextQuestion();
        }

        BinaryChoiceModeFragment binaryChoiceModeFragment = (BinaryChoiceModeFragment) getFragmentManager()
                .findFragmentByTag("BinaryChoiceModeFragment");
        if (binaryChoiceModeFragment != null) {
            binaryChoiceModeFragment.loadNextQuestion();
        }

    }

    @Override
    public void onPlayAgainButtonClicked() {
        secondaryLoadActivity();
    }

    @Override
    public void onModeChanged(boolean isMultipleModeEnabled) {
        SessionManager sessionManager = SessionManager.getInstance(this);
        boolean isBinaryModeEnabled = ! isMultipleModeEnabled;
        sessionManager.setIsBinaryModeQuizEnabled(isBinaryModeEnabled);

        // Select the first tab manually
        TabLayout.Tab newTab = tabBar.getTabAt(0);
        newTab.select();

        secondaryLoadActivity();
    }
}
