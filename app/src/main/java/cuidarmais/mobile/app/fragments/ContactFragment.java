package cuidarmais.mobile.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import cuidarmais.mobile.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_contact, container, false);
        EditText messageText = (EditText) view.findViewById(R.id.messageTextView);
        //Set keyboard settings to display DONE button with multiline text
        messageText.setImeOptions(messageText.getImeOptions() | EditorInfo.IME_ACTION_DONE);
        messageText.setRawInputType(InputType.TYPE_CLASS_TEXT);

        return  view;
    }

}
