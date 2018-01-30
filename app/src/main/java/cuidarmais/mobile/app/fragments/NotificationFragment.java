package cuidarmais.mobile.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cuidarmais.mobile.app.R;
import cuidarmais.mobile.model.Notification;

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
        List<Notification> notifications = loadNotifications();

        ArrayAdapter<Notification> adapter = new ArrayAdapter<Notification>(getActivity(),
                android.R.layout.simple_list_item_1, notifications);

        notificationList.setAdapter(adapter);
        return view;
    }

    private List<Notification> loadNotifications(){
        List<Notification> notes = new ArrayList<>();
        Notification n1 = new Notification(new Date(),"Title 1","Text 1", "Author A");
        Notification n2 = new Notification(new Date(),"Title 2","Text 2", "Author B");
        Notification n3 = new Notification(new Date(),"Title 3","Text 3", "Author C");
        Notification n4 = new Notification(new Date(),"Title 4","Text 4", "Author D");
        Notification n5 = new Notification(new Date(),"Title 5","Text 5", "Author E");
        notes.add(n1);
        notes.add(n2);
        notes.add(n3);
        notes.add(n4);
        notes.add(n5);
        return notes;
    }
}
