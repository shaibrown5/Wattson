package com.example.wattson;

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
import com.example.wattson.utils.SpacingItemDecorator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UtilityInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UtilityInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView m_backArrow;
    TextView m_Day;
    TextView m_Week;
    TextView m_Month;
    TextView m_Year;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UtilityInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UtilityInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UtilityInfoFragment newInstance(String param1, String param2) {
        UtilityInfoFragment fragment = new UtilityInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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