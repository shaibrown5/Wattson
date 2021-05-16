package com.example.wattson.main_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wattson.Adapter.UtilityAdapter;
import com.example.wattson.HomeActivity;
import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.R;
import com.example.wattson.utils.SpacingItemDecorator;

import java.util.ArrayList;

public class UtilityInfoFragment extends Fragment {

    private TextView m_backArrow;
    private TextView txt_Day;
    private TextView txt_Week;
    private TextView txt_Month;
    private TextView txt_Year;
    private TextView m_currentTimePicked;
    private StateTime m_StateTime;
    private HomeActivity ac_HomeActivity;
    ArrayList<ApplianceInfo> m_ApplianceInfo = new ArrayList<>();


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
        ac_HomeActivity = (HomeActivity) getActivity();
        m_ApplianceInfo = ac_HomeActivity.getApplianceList();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_backArrow = (TextView) getView().findViewById(R.id.UtilBackArrow);
        txt_Day = (TextView) getView().findViewById(R.id.textViewDay);
        txt_Week = (TextView) getView().findViewById(R.id.textViewWeek);
        txt_Month= (TextView) getView().findViewById(R.id.textViewMonth);
        txt_Year = (TextView) getView().findViewById(R.id.textViewYear);

        m_currentTimePicked = txt_Day;
        m_StateTime = StateTime.DAY;
        setCurrentTimePicked();

        ApplianceInfo currentApplication = m_ApplianceInfo.get(0);
        String[] values = {currentApplication.getApplianceName(), currentApplication.getLastReading().getTimeStamp(), currentApplication.getLastReading().getReading()};
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
        txt_Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = StateTime.DAY;
                m_currentTimePicked = txt_Day;
                setCurrentTimePicked();
            }
        });

        //change to week view
        txt_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = StateTime.WEEK;
                m_currentTimePicked = txt_Week;
                setCurrentTimePicked();
            }
        });

        //change to month view
        txt_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = StateTime.MONTH;
                m_currentTimePicked = txt_Month;
                setCurrentTimePicked();
            }
        });

        //change to year view
        txt_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = StateTime.YEAR;
                m_currentTimePicked = txt_Year;
                setCurrentTimePicked();
            }
        });

    }

    /**
     * this meths sets the time fram picked (day, week ...)
     */
    private void setCurrentTimePicked(){
        m_currentTimePicked.setClickable(false);
        SpannableString content = new SpannableString(m_StateTime.getValue());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        m_currentTimePicked.setText(content);
        m_currentTimePicked.setTextColor(Color.BLACK);
    }

    /**
     * This method clears the
     */
    private void clearCurrentTime(){
        m_currentTimePicked.setText(m_StateTime.getValue());
        m_currentTimePicked.setTextColor(getResources().getColor(R.color.font_gray));
        m_currentTimePicked.setClickable(true);
    }

    public enum StateTime {
        DAY("Day"), WEEK("WK"), MONTH("MO"), YEAR("YR");
        private final String value;

        StateTime(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}