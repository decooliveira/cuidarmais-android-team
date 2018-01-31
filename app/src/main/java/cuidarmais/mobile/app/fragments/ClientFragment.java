package cuidarmais.mobile.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cuidarmais.mobile.app.R;

public class ClientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    public ClientFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViews();
        return inflater.inflate(R.layout.client_fragment, container, false);
    }



}
