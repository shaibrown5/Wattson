package com.example.wattson.main_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wattson.Adapter.UtilityAdapter;
import com.example.wattson.R;
import com.example.wattson.utils.SpacingItemDecorator;

public class UtilityInfoFragment extends Fragment {

    TextView m_backArrow;
    TextView m_Day;
    TextView m_Week;
    TextView m_Month;
    TextView m_Year;


    public UtilityInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_utility_info, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_backArrow = (TextView) getView().findViewById(R.id.UtilBackArrow);
        m_Day = (TextView) getView().findViewById(R.id.textViewDay);
        m_Week = (TextView) getView().findViewById(R.id.textViewWeek);
        m_Month= (TextView) getView().findViewById(R.id.textViewMonth);
        m_Year = (TextView) getView().findViewById(R.id.textViewYear);

        m_Day.setClickable(false);

        String[] values = {"Shai", "Brown", "yo"};
        String[] labels = getResources().getStringArray(R.array.utility_header_list);

        RecyclerView rView = (RecyclerView)getView().findViewById(R.id.recycleViewUtility);
        UtilityAdapter myAdapter = new UtilityAdapter(getContext(), labels, values);
        rView.setAdapter(myAdapter);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        // change this to create a larger margin between the items in recycle view
        SpacingItemDecorator itemDecor = new SpacingItemDecorator(50);
        rView.addItemDecoration(itemDecor);

        TextView t = (TextView) getView().findViewById(R.id.textViewOnUtilityPage);
        //TODO THIS MAKES STUFF INVISIBLE
        //t.setVisibility(View.INVISIBLE);
        t.setVisibility(View.VISIBLE);



        // Go back to the Home Page
        m_backArrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Fragment someFragment = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        //change to day view
        m_Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTimeMeasurementBar();
                m_Day.setTextColor(Color.BLACK);
                m_Day.setClickable(false);

            }
        });

        //change to week view
        m_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTimeMeasurementBar();
                m_Week.setTextColor(Color.BLACK);
                m_Week.setClickable(false);

            }
        });

        //change to month view
        m_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTimeMeasurementBar();
                m_Month.setTextColor(Color.BLACK);
                m_Month.setClickable(false);

            }
        });

        //change to year view
        m_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTimeMeasurementBar();
                m_Year.setTextColor(Color.BLACK);
                m_Year.setClickable(false);

            }
        });

    }

    /**
     * set up the measurements bar
     */
    private void setUpTimeMeasurementBar(){
        //changing the current color
        m_Day.setTextColor(getResources().getColor(R.color.font_gray));
        m_Week.setTextColor(getResources().getColor(R.color.font_gray));
        m_Month.setTextColor(getResources().getColor(R.color.font_gray));
        m_Year.setTextColor(getResources().getColor(R.color.font_gray));

        m_Day.setClickable(true);
        m_Week.setClickable(true);
        m_Month.setClickable(true);
        m_Year.setClickable(true);
    }
}