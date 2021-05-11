package com.example.wattson.main_fragments;

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

import com.example.wattson.R;

public class StatisticsFragment extends Fragment {

    private TextView txt_Day;
    private TextView txt_Week;
    private TextView txt_Month;
    private TextView txt_Year;
    private TextView m_currentTimePicked;
    private UtilityInfoFragment.StateTime m_StateTime;

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
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_Day = (TextView) getView().findViewById(R.id.textStatisticsDay);
        txt_Week = (TextView) getView().findViewById(R.id.textStatisticsWeek);
        txt_Month= (TextView) getView().findViewById(R.id.textStatisticsMonth);
        txt_Year = (TextView) getView().findViewById(R.id.textStatisticsYear);

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