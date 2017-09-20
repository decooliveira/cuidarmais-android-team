package cuidarmais.org.cuidarmais.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cuidarmais.org.cuidarmais.R;
import cuidarmais.org.model.Notification;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);
        ListView notificationList = (ListView) view.findViewById(R.id.notificationList);
        List<Notification> notifications = null;

        ArrayAdapter<Notification> adapter = new ArrayAdapter<Notification>(getActivity(),
                android.R.layout.simple_list_item_1, notifications);

        notificationList.setAdapter(adapter);
        return view;
    }

}
