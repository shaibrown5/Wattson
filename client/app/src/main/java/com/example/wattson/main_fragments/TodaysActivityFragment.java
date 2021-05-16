package com.example.wattson.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wattson.HomeActivity;
import com.example.wattson.R;

public class TodaysActivityFragment extends Fragment {

    private HomeActivity ac_HomeActivity;
    private RecyclerView m_rView;

    public TodaysActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todays_activity, container, false);

        ac_HomeActivity = (HomeActivity) getActivity();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        m_rView = getView().findViewById(R.id.recyclerActivityLog);
    }
}