package com.flatrocktechnology.android.famousquotequiz.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flatrocktechnology.android.famousquotequiz.R;
import com.flatrocktechnology.android.famousquotequiz.activities.MainActivity;


/* Fragment for showing statistics and option to play again */
public class PlayAgainFragment extends Fragment {

    private OnPlayAgainClickListener mListener;
    private int correctAnswers;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnPlayAgainClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnPlayAgainClickListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnPlayAgainClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPlayAgainClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correctAnswers = getArguments().getInt(MainActivity.PLAY_AGAIN_BUNDLE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_play_again, container, false);

        // Make the result text message
        String resultText = getString(R.string.result_part1) + " " + correctAnswers +
                " " + getString(R.string.result_part2);

        // Set text on textView
        TextView tvResults = (TextView) rootView.findViewById(R.id.tvResults);
        tvResults.setText(resultText);

        // Handle on click event
        Button btnPlayAgain = (Button) rootView.findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPlayAgainButtonClicked();
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnPlayAgainClickListener {
        void onPlayAgainButtonClicked();
    }
}
