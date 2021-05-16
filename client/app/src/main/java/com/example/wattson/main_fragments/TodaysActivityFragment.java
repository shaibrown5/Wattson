package com.example.wattson.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wattson.Adapter.ActivityAdapter;
import com.example.wattson.Adapter.UtilityAdapter;
import com.example.wattson.HomeActivity;
import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.R;
import com.example.wattson.utils.SpacingItemDecorator;

import java.util.ArrayList;

public class TodaysActivityFragment extends Fragment {

    private HomeActivity ac_HomeActivity;
    private RecyclerView m_rView;
    private ArrayList<ApplianceInfo> m_ApplianceInfo = new ArrayList<>();

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
        m_ApplianceInfo = ac_HomeActivity.getApplianceList();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        m_rView = getView().findViewById(R.id.recyclerActivityLog);


        String[] names = {"Kettle", "Oven"};
        String[] onTime = {"8:00 am", "9:15 am"};
        String[] offTime = {"8:23 am", "11:15 am"};

        ActivityAdapter myAdapter = new ActivityAdapter(getContext(), names, onTime, offTime);
        m_rView.setAdapter(myAdapter);
        m_rView.setLayoutManager(new LinearLayoutManager(getContext()));
        // change this to create a larger margin between the items in recycle view
        SpacingItemDecorator itemDecor = new SpacingItemDecorator(30);
        m_rView.addItemDecoration(itemDecor);

        
    }
}