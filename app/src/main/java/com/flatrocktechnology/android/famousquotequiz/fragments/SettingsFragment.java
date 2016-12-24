package com.flatrocktechnology.android.famousquotequiz.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.flatrocktechnology.android.famousquotequiz.R;
import com.flatrocktechnology.android.famousquotequiz.sessionmanager.SessionManager;

/* Fragment for user to change the game mode*/
public class SettingsFragment extends Fragment {

    private OnSettingsClickListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnSettingsClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSettingsClickListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnSettingsClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSettingsClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final Switch switchMode = (Switch) rootView.findViewById(R.id.sMultipleModeEnabled);

        // Make the switch show the correct values
        SessionManager sessionManager = SessionManager.getInstance(getActivity());
        // Invert the state of the button
        boolean isMultipleChoiceEnabled  = ! sessionManager.isBinaryModeQuizEnabled();
        Log.d("Settings", "onCreateView:  multiple mode state " + isMultipleChoiceEnabled + " binary mode " + sessionManager.isBinaryModeQuizEnabled()) ;
        switchMode.setChecked(isMultipleChoiceEnabled);

        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mListener.onModeChanged(isChecked);
            }
        });

        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSettingsClickListener {
        void onModeChanged(final boolean isBinaryModeEnabled);
    }
}
