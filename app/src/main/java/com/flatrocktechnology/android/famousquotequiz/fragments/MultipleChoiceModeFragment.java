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

/* Fragment for showing question with three possible answers*/
public final class MultipleChoiceModeFragment extends Fragment {
    private static final String TAG = MultipleChoiceModeFragment.class.getSimpleName();

    private OnAnswerClickListener mListener;
    private ArrayList<QuizQuestionDao> questionList;
    private int progressCount;
    private int correctAnswersCount;
    private TextView tvQuestion;
    private Button btnFirstAnswer;
    private Button btnSecondAnswer;
    private Button btnThirdAnswer;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnAnswerClickListener) context;
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
            mListener = (OnAnswerClickListener) activity;
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
            Log.d(TAG, "MultipleChoiceModeFragment exception " + questionList);
            // TODO Show error dialog
        }

        progressCount = 0;
        correctAnswersCount = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_multiple_choise_mode, container, false);

        initializeLayout(rootView);

        showNextQuestionOnScreen();

        setOnClickListeners();

        return rootView;
    }

    private void initializeLayout(View rootView) {
        tvQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        btnFirstAnswer = (Button) rootView.findViewById(R.id.btnAnswerA);
        btnSecondAnswer = (Button) rootView.findViewById(R.id.btnAnswerB);
        btnThirdAnswer = (Button) rootView.findViewById(R.id.btnAnswerC);
    }

    private void setOnClickListeners() {
        btnFirstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String textOnButton = btnFirstAnswer.getText().toString();
                handleUserButtonClick(textOnButton);
            }
        });

        btnSecondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String textOnButton = btnSecondAnswer.getText().toString();
                handleUserButtonClick(textOnButton);
            }
        });

        btnThirdAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String textOnButton = btnThirdAnswer.getText().toString();
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
        String dialogTitle;
        QuizQuestionDao currentQuestion = questionList.get(progressCount);
        String correctAnswer = currentQuestion.getCorrAnswer();

        // Compare weather the passed text is the correct answer to the question
        if (correctAnswer.equals(textOnButton)) {
            correctAnswersCount++;
            Log.d(TAG, "correctAnswersCount: " + correctAnswersCount);
            // Compose title for dialog;
            dialogTitle = getString(R.string.correct_answer) + " " + correctAnswer;
        } else {
            // Compose title for dialog
            dialogTitle = getString(R.string.wrong_answer) + " " + correctAnswer;
        }

        mListener.showDialog(dialogTitle);

        // Update the counter
        progressCount++;
    }


    /* Update screen with the question and the possible asnwers */
    private void showNextQuestionOnScreen() {
        QuizQuestionDao dao = questionList.get(progressCount);
        tvQuestion.setText("\"" + dao.getQuestion() + "\"");

        // Put the answers in list to be randomized.
        ArrayList<String> list = new ArrayList<>(Arrays.asList(dao.getCorrAnswer(),
                dao.getFirstWrongAnswer(), dao.getSecondWrongAnswer()));

        randomizeElementsPosition(list);

        btnFirstAnswer.setText(list.get(0));
        btnSecondAnswer.setText(list.get(1));
        btnThirdAnswer.setText(list.get(2));
    }

    private void randomizeElementsPosition(ArrayList<String> list) {
        // Swap first and second
        int max = 10;
        int middle = 5;
        Random random = new Random();
        int rand = random.nextInt(max);
        if (rand < middle) { // used to randomly enter in the if
            String swap = list.get(1);
            list.set(1, list.get(0));
            list.set(0, swap);
        }

        //Swap second and third word
        rand = random.nextInt(max);
        if (rand > middle) {  // used to randomly enter in the if
            String swap = list.get(2);
            list.set(2, list.get(1));
            list.set(1, swap);
        }
    }

    /*
     Call this method from outside the fragment to load and show the next question
     on screen. If we reached the end of the quiz show the PlayAgainFragment.
     */
    public void loadNextQuestion() {
        Log.d(TAG, " loadNextQuestion progresscount: " + progressCount);
        if (progressCount < 10) {
            showNextQuestionOnScreen();
        } else {
            // TODO check logic
            mListener.finishedQuiz(correctAnswersCount);
            progressCount = 0;
        }
    }

    public interface OnAnswerClickListener {

        void finishedQuiz(final int correctAnswers);

        void showDialog(String dialogTitle);
    }
}
