package cuidarmais.org.cuidarmais.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cuidarmais.org.cuidarmais.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationDetailFragment extends Fragment {


    public NotificationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_detail, container, false);
    }

}
