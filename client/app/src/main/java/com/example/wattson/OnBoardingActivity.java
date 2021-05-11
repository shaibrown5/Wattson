package com.example.wattson;
// https://github.com/kofigyan/StateProgressBar


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.wattson.on_boarding_fragments.OBpage1;
import com.example.wattson.on_boarding_fragments.OBpage2;
import com.example.wattson.on_boarding_fragments.OBpage3;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class OnBoardingActivity extends AppCompatActivity {

    private StateProgressBar m_stateProgressBar;
    private StateProgressBar.StateNumber m_stateNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        m_stateProgressBar = (StateProgressBar) findViewById(R.id.progress_bar_id);
        m_stateProgressBar.setAnimationDuration(10000);

        m_stateNumber = StateProgressBar.StateNumber.ONE;
        Fragment fragment = new OBpage1();
        loadFragment(fragment);
        m_stateProgressBar.setCurrentStateNumber(m_stateNumber);
    }


    public void incrementBar(){
        Fragment fragment = null;
        boolean loadFragment = true;

        switch (m_stateNumber.getValue()){
            case 1:
                m_stateNumber = StateProgressBar.StateNumber.TWO;
                fragment = new OBpage2();
                break;
            case 2:
                m_stateNumber = StateProgressBar.StateNumber.THREE;
                fragment = new OBpage3();
                break;
            case 3:
                loadFragment = false;
                m_stateProgressBar.checkStateCompleted(true);
                m_stateProgressBar.enableAnimationToCurrentState(true);
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        if(loadFragment){
            loadFragment(fragment);
            m_stateProgressBar.setCurrentStateNumber(m_stateNumber);
        }
    }

    private void loadFragment(Fragment fragment) {
        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_on_boarding_layout, fragment)
                .commit();
    }
}
