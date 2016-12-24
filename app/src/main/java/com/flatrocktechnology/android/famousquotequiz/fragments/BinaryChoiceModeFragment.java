package com.flatrocktechnology.android.famousquotequiz.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flatrocktechnology.android.famousquotequiz.R;
import com.flatrocktechnology.android.famousquotequiz.activities.MainActivity;
import com.flatrocktechnology.android.famousquotequiz.model.QuizQuestionDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BinaryChoiceModeFragment extends Fragment {

    private static final String TAG = BinaryChoiceModeFragment.class.getSimpleName();
    private OnYesNoAnswerClickListener mListener;
    private ArrayList<QuizQuestionDao> questionList;
    private int progressCount;
    private int correctAnswersCount;
    private String possibleAnswer;
    private TextView tvQuestion;
    private TextView tvPossibleAnswer;
    private Button btnTrue;
    private Button btnFalse;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnYesNoAnswerClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnYesNoAnswerClickListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnYesNoAnswerClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnYesNoAnswerClickListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            questionList = (ArrayList<QuizQuestionDao>) getArguments().getSerializable(MainActivity.QUESTIONS_BUNDLE_KEY);
        } catch (Exception e) {
            Log.d(TAG, "BinaryFragment exception " + questionList);
            // TODO Show error dialog
        }

        progressCount = 0;
        correctAnswersCount = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_binary_choise_mode, container, false);

        initializeLayout(rootView);

        showNextQuestionOnScreen();

        setOnClickListeners();

        return rootView;
    }

    private void initializeLayout(View rootView) {
        tvQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        tvPossibleAnswer = (TextView) rootView.findViewById(R.id.tvPossibleAnswer);
        btnTrue = (Button) rootView.findViewById(R.id.btnTrue);
        btnFalse = (Button) rootView.findViewById(R.id.btnFalse);
    }

    private void setOnClickListeners() {
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String textOnButton = btnTrue.getText().toString();
                handleUserButtonClick(textOnButton);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String textOnButton = btnFalse.getText().toString();
                handleUserButtonClick(textOnButton);
            }
        });
    }

    /* This method check weather the selected button from user was the
       correct answer to the question, then inform him by showing dialog
       and after that load the next question on screen until the end of the
       quiz.
     */
    private void handleUserButtonClick(String textOnButton) {
        String dialogTitle = "";
        QuizQuestionDao currentQuestion = questionList.get(progressCount);
        String correctAnswer = currentQuestion.getCorrAnswer();

        // Compare weather the showing possible answer is the correct answer to the question
        if (correctAnswer.equals(possibleAnswer)) {

            //Check weather the user clicked Yes button
            if (textOnButton.equals(getString(R.string.yes))) {
                correctAnswersCount++;
                Log.d(TAG, "correctAnswersCount: " + correctAnswersCount);
                // Compose title for dialog;
                dialogTitle = getString(R.string.correct_answer) + " " + correctAnswer;
            } else {
                // The user answered wrong.
                dialogTitle = getString(R.string.wrong_answer) + " " + correctAnswer;
            }
        } else if (!correctAnswer.equals(possibleAnswer)) {

            //Check weather the user clicked No button
            if (textOnButton.equals(getString(R.string.no))) {
                correctAnswersCount++;
                // Compose title for dialog;
                dialogTitle = getString(R.string.correct_answer) + " " + correctAnswer;
            } else {
                // The user answered wrong.
                dialogTitle = getString(R.string.wrong_answer) + " " + correctAnswer;
            }
        } else {
            // The user answered wrong.
            dialogTitle = getString(R.string.wrong_answer) + " " + correctAnswer;
        }

        mListener.showDialog(dialogTitle);

        // Update the counter
        progressCount++;
    }


    /* Update screen with the question and one  possible answer */
    private void showNextQuestionOnScreen() {
        QuizQuestionDao dao = questionList.get(progressCount);
        tvQuestion.setText("\"" + dao.getQuestion() + "\"");

        // Put the answers in list to be randomized.
        ArrayList<String> list = new ArrayList<>(Arrays.asList(dao.getCorrAnswer(),
                dao.getFirstWrongAnswer(), dao.getSecondWrongAnswer()));

        possibleAnswer = getRandomElementFromList(list);
        // Show the possible answer
        tvPossibleAnswer.setText(possibleAnswer);
    }

    private String getRandomElementFromList(ArrayList<String> list) {
        Random random = new Random();
        int rand = random.nextInt(list.size() - 1);
        String text = list.get(rand);
        return text;
    }

    /*
     Call this method from outside the fragment to load and show the next question
     on screen. If we reached the end of the quiz show the PlayAgainFragment.
     */
    public void loadNextQuestion() {
        Log.d(TAG, "progresscount: " + progressCount);
        if (progressCount < 10) {
            showNextQuestionOnScreen();
        } else {
            mListener.finishedQuiz(correctAnswersCount);
            progressCount = 0;
        }
    }

    public interface OnYesNoAnswerClickListener {

        void finishedQuiz(final int correctAnswers);

        void showDialog(String dialogTitle);
    }
}


