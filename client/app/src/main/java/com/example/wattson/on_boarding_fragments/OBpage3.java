package com.example.wattson.on_boarding_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wattson.OnBoardingActivity;
import com.example.wattson.R;

public class OBpage3 extends Fragment {

    private Button bt_doneButton;
    private TextView txt_skip;
    private TextView txt_plugNumber;
    private boolean m_isLast = false;
    private int m_plugNum;

    public OBpage3() {
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
        m_plugNum = 1;
        return inflater.inflate(R.layout.fragment_ob_page3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_doneButton = (Button) getView().findViewById(R.id.button3Done);
        txt_skip = (TextView) getView().findViewById(R.id.textSkip);
        txt_plugNumber = (TextView) getView().findViewById(R.id.textPlugNumber);
        txt_plugNumber.setText("plug " + m_plugNum);

        bt_doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(m_isLast) {
                    ((OnBoardingActivity) getActivity()).incrementBar();
                }
                else {
                    incrementPlugNum();
                }
            }
        });

        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_doneButton.setText("Done");
                m_isLast = true;
            }
        });

    }

    /**
     * This methd imcrements the plug number
     */
    private void incrementPlugNum(){
        m_plugNum++;
        String plugText = "plug " + m_plugNum;
        txt_plugNumber.setText(plugText);

        if (m_plugNum == 5){
            bt_doneButton.setText("Done");
            m_isLast = true;
        }
    }
}
