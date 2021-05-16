package com.example.wattson.main_fragments;

// https://github.com/PhilJay/MPAndroidChart

import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wattson.HomeActivity;
import com.example.wattson.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatisticsFragment extends Fragment {

    private TextView txt_Day;
    private TextView txt_Week;
    private TextView txt_Month;
    private TextView txt_Year;
    private TextView m_currentTimePicked;
    private UtilityInfoFragment.StateTime m_StateTime;
    private String[] m_dateArray;
    private BarChart bc_BarChart;
    private ArrayList<BarEntry> m_barEntryArrayList;
    private ArrayList<String> m_labelsNames;
    private HomeActivity ac_HomeActivity;

    public StatisticsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        // get the labels for day time
        m_barEntryArrayList = new ArrayList<>();
        m_labelsNames = new ArrayList<>();
        m_labelsNames.clear();
        m_barEntryArrayList.clear();

        m_dateArray = getResources().getStringArray(R.array.analytics_day);
        ac_HomeActivity = (HomeActivity) getActivity();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_Day = (TextView) getView().findViewById(R.id.textStatisticsDay);
        txt_Week = (TextView) getView().findViewById(R.id.textStatisticsWeek);
        txt_Month= (TextView) getView().findViewById(R.id.textStatisticsMonth);
        txt_Year = (TextView) getView().findViewById(R.id.textStatisticsYear);

        bc_BarChart = getView().findViewById(R.id.barChart);

        m_currentTimePicked = txt_Day;
        m_StateTime = UtilityInfoFragment.StateTime.DAY;
        setCurrentTimePicked();


        //change to day view
        txt_Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = UtilityInfoFragment.StateTime.DAY;
                m_currentTimePicked = txt_Day;
                m_dateArray = getResources().getStringArray(R.array.analytics_day);
                setCurrentTimePicked();
            }
        });

        //change to week view
        txt_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = UtilityInfoFragment.StateTime.WEEK;
                m_currentTimePicked = txt_Week;
                m_dateArray = getResources().getStringArray(R.array.analytics_week);
                setCurrentTimePicked();
            }
        });

        //change to month view
        txt_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = UtilityInfoFragment.StateTime.MONTH;
                m_currentTimePicked = txt_Month;
                m_dateArray = getResources().getStringArray(R.array.analytics_month);
                setCurrentTimePicked();
            }
        });

        //change to year view
        txt_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCurrentTime();
                m_StateTime = UtilityInfoFragment.StateTime.YEAR;
                m_currentTimePicked = txt_Year;
                m_dateArray = getResources().getStringArray(R.array.analytics_year);
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

        //deals with the bar lists
        m_labelsNames.clear();
        m_barEntryArrayList.clear();
        Collections.addAll(m_labelsNames, m_dateArray);
        setBarChart();

    }

    /**
     * This method clears the
     */
    private void clearCurrentTime(){
        m_currentTimePicked.setText(m_StateTime.getValue());
        m_currentTimePicked.setTextColor(getResources().getColor(R.color.font_gray));
        m_currentTimePicked.setClickable(true);
    }

    private void setBarChart(){

        // sets up dummy info
        for (int i = 0; i < m_labelsNames.size() ; i++) {
            int num = (int)Math.floor(Math.random()*(100)+6);
            m_barEntryArrayList.add(new BarEntry(i, num));
        }

        BarDataSet bds = new BarDataSet(m_barEntryArrayList, "info");
        bds.setColors(getResources().getColor(R.color.new_background_blue));
        Description des = new Description();
        des.setText("");
        bc_BarChart.setDescription(des);
        BarData bd = new BarData(bds);
        bd.setBarWidth(0.5f);
        bc_BarChart.setData(bd);

        //set x axis labels
        XAxis xAxis = bc_BarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(m_labelsNames));
        //set position of lables
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
//        xAxis.setGranularity(1f);
//        xAxis.setLabelCount(m_labelsNames.size());
        bc_BarChart.animateY(1600);
        bc_BarChart.invalidate();
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