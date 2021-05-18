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
    private boolean m_optionPicked = false;
    private boolean m_hasSkipped = false;
    private int m_plugNum;
    private String m_clickedButtonName;
    private final String m_toastMessage = "                Item already picked\nClick next, skip or double click to reset";

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
                if(m_optionPicked || m_hasSkipped) {
                    if (m_isLast) {
                        ((OnBoardingActivity) getActivity()).incrementBar();
                    } else {
                        incrementPlugNum();
                        txt_skip.setVisibility(View.VISIBLE);
                        txt_skip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bt_doneButton.setText("Done");
                                m_isLast = true;
                                m_hasSkipped = true;
                            }
                        });
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please pick an Item", Toast.LENGTH_SHORT).show();
                }
            }
        });



        bt_kettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_kettle;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_kettle.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_washingMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_washingMachine;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_washingMachine.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_microwave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_microwave;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_microwave.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_tv;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_tv.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_iron;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_iron.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_ac;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_ac.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_oven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_oven;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_oven.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_computer;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_computer.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_dishWasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_dishWasher;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_dishWasher.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_optionPicked) {
                    bt_clickedButton = bt_other;
                    setCLickedOption();
                }
                else if(bt_clickedButton.getText().toString().equals(bt_other.getText().toString())){
                    doUnclickButton();
                }
                else{
                    Toast.makeText(getContext(),m_toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Sets the clicked button when it is chosen
     */
    private void setCLickedOption(){
        m_clickedButtonName = bt_clickedButton.getText().toString();
        bt_clickedButton.setBackgroundColor(getResources().getColor(R.color.new_background_blue));
        m_optionPicked = true;
    }

    private void doUnclickButton(){
        bt_clickedButton.setBackgroundColor(Color.WHITE);
        m_optionPicked = false;
        m_clickedButtonName = "";
        bt_clickedButton = null;
    }

    /**
     * This methd imcrements the plug number, reests the button colors
     */
    private void incrementPlugNum(){
        m_plugNum++;
        String plugText = "Plug " + m_plugNum;
        txt_plugNumber.setText(plugText);
        doUnclickButton();

        if (m_plugNum == 5){
            bt_doneButton.setText("Done");
            m_isLast = true;
        }
    }
}
