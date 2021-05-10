package com.example.wattson.on_boarding_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wattson.OnBoardingActivity;
import com.example.wattson.R;
import com.google.android.material.button.MaterialButton;

public class OBpage3 extends Fragment {

    private Button bt_doneButton;
    private TextView txt_skip;
    private TextView txt_plugNumber;
    private MaterialButton bt_clickedButton;
    private boolean m_isLast = false;
    private int m_plugNum;
    private boolean m_optionPicked = false;

    private MaterialButton bt_kettle;
    private MaterialButton bt_washingMachine;
    private MaterialButton bt_microwave;
    private MaterialButton bt_tv;
    private MaterialButton bt_iron;
    private MaterialButton bt_ac;
    private MaterialButton bt_oven;
    private MaterialButton bt_computer;
    private MaterialButton bt_dishWasher;
    private MaterialButton bt_other;


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
        txt_plugNumber.setText("Plug " + m_plugNum);

        bt_kettle = (MaterialButton) getView().findViewById(R.id.buttonKettle);
        bt_washingMachine = (MaterialButton) getView().findViewById(R.id.buttonWashingMachine);
        bt_microwave = (MaterialButton) getView().findViewById(R.id.buttonMicro);
        bt_tv = (MaterialButton) getView().findViewById(R.id.buttonTv);
        bt_iron = (MaterialButton) getView().findViewById(R.id.buttonIron);
        bt_ac = (MaterialButton) getView().findViewById(R.id.buttonAc);
        bt_oven = (MaterialButton) getView().findViewById(R.id.buttonOven);
        bt_computer = (MaterialButton) getView().findViewById(R.id.buttonComputer);
        bt_dishWasher = (MaterialButton) getView().findViewById(R.id.buttonDishWasher);
        bt_other = (MaterialButton) getView().findViewById(R.id.buttonOther);

        txt_skip.setVisibility(View.INVISIBLE);

        bt_doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_optionPicked) {
                    if (m_isLast) {
                        ((OnBoardingActivity) getActivity()).incrementBar();
                    } else {
                        incrementPlugNum();
                        txt_skip.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please pick an Item", Toast.LENGTH_SHORT).show();
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

        bt_kettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_kettle;
                    bt_kettle.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_washingMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_washingMachine;
                    bt_washingMachine.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_microwave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_microwave;
                    bt_microwave.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_tv;
                    bt_tv.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_iron;
                    bt_iron.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_ac;
                    bt_ac.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_oven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_oven;
                    bt_oven.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_computer;
                    bt_computer.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_dishWasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_dishWasher;
                    bt_dishWasher.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

        bt_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_other;
                    bt_other.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
                    m_optionPicked = true;
                }
            }
        });

    }

    /**
     * This methd imcrements the plug number, reests the button colors
     */
    private void incrementPlugNum(){
        m_plugNum++;
        String plugText = "Plug " + m_plugNum;
        txt_plugNumber.setText(plugText);
        bt_clickedButton.setBackgroundColor(Color.WHITE);
        m_optionPicked = false;

        if (m_plugNum == 5){
            bt_doneButton.setText("Done");
            m_isLast = true;
        }
    }
}
