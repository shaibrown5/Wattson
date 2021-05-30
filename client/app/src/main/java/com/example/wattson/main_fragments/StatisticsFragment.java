package com.example.wattson.main_fragments;

// https://github.com/PhilJay/MPAndroidChart

import android.graphics.Color;

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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticsFragment extends Fragment {

    private TextView txt_Day;
    private TextView txt_Week;
    private TextView txt_Month;
    private TextView txt_Year;
    private TextView m_currentTimePicked;
    private UtilityInfoFragment.StateTime m_StateTime;
    private MaterialButton bt_percentButton;
    private MaterialButton bt_moneyButton;
    private String[] m_dateArray;

    // bar chart
    private BarChart bc_BarChart;
    private ArrayList<BarEntry> m_barEntryArrayList;
    private ArrayList<String> m_barLabelsNames;
    private HomeActivity ac_HomeActivity;

    //pie chart
    private int[] m_colorsArrays;
    private PieChart p_pieChart;
    private ArrayList<PieEntry> m_pieEntryArrayList;
    private ArrayList<String> m_pielabelsNames;

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
        m_barLabelsNames = new ArrayList<>();
        m_barLabelsNames.clear();
        m_barEntryArrayList.clear();

        m_pieEntryArrayList = new ArrayList<>();
        m_pielabelsNames = new ArrayList<>();
        m_pielabelsNames.clear();
        m_pieEntryArrayList.clear();

        m_colorsArrays = new int[]{getResources().getColor(R.color.card1),
                getResources().getColor(R.color.card2),
                getResources().getColor(R.color.card3),
                getResources().getColor(R.color.card4),
                getResources().getColor(R.color.card5) };

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
        bt_percentButton = (MaterialButton) getView().findViewById(R.id.btnPercentage);
        bt_moneyButton = (MaterialButton) getView().findViewById(R.id.btnMoney);

        bc_BarChart = getView().findViewById(R.id.barChart);
        p_pieChart = (PieChart) getView().findViewById(R.id.pieChart);
        p_pieChart.setVisibility(View.INVISIBLE);

        m_currentTimePicked = txt_Day;
        m_StateTime = UtilityInfoFragment.StateTime.DAY;
        setCurrentTimePicked();

        bt_percentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bc_BarChart.setVisibility(View.INVISIBLE);
                p_pieChart.setVisibility(View.VISIBLE);
                setPieChart();
            }
        });

        bt_moneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_pieChart.setVisibility(View.INVISIBLE);
                bc_BarChart.setVisibility(View.VISIBLE);
                setBarChart();
            }
        });

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
        m_barLabelsNames.clear();
        m_barEntryArrayList.clear();
        Collections.addAll(m_barLabelsNames, m_dateArray);
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

    /**
     * This method sets up the bar graph
     */
    private void setBarChart(){
        m_barEntryArrayList.clear();
        // sets up dummy info
        for (int i = 0; i < m_barLabelsNames.size() ; i++) {
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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(m_barLabelsNames));
        //set position of lables
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
//        xAxis.setGranularity(1f);
//        xAxis.setLabelCount(m_barLabelsNames.size());
        bc_BarChart.animateY(1000);
        bc_BarChart.invalidate();
    }

    /**
     * This method sets up the pie graph
     */
    private void setPieChart(){
        p_pieChart = (PieChart) getView().findViewById(R.id.pieChart);
        p_pieChart.setUsePercentValues(true);
        p_pieChart.getDescription().setEnabled(false);
        p_pieChart.setUsePercentValues(true);
        p_pieChart.setExtraOffsets(5,10,5,5);
        p_pieChart.setDragDecelerationFrictionCoef(0.95f);
        p_pieChart.setDrawHoleEnabled(false);
        m_pieEntryArrayList.clear();
        m_pieEntryArrayList.add(new PieEntry(10, "Ac"));
        m_pieEntryArrayList.add(new PieEntry(20f, "Oven"));
        m_pieEntryArrayList.add(new PieEntry(15f, "dish washer"));
        m_pieEntryArrayList.add(new PieEntry(40f, "kettle"));
        m_pieEntryArrayList.add(new PieEntry(15, "washing"));

        p_pieChart.animateY(1500, Easing.EaseInOutCubic);

        PieDataSet pds = new PieDataSet(m_pieEntryArrayList, "");
        pds.setSliceSpace(2f);
        pds.setSelectionShift(5f);
        pds.setColors(m_colorsArrays);

        PieData data = new PieData(pds);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        p_pieChart.setData(data);
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
