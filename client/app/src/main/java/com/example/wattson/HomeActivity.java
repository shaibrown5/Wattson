package com.example.wattson;
//https://github.com/oneHamidreza/MeowBottomNavigationJava

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.main_fragments.HomeFragment;
import com.example.wattson.main_fragments.SettingsFragment;
import com.example.wattson.main_fragments.StatisticsFragment;
import com.example.wattson.main_fragments.TodaysActivityFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    private static final String TAG = "HomePage";
    private ArrayList<ApplianceInfo> m_ApplianceInfo;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "in home page");
        bottomNavigation = findViewById(R.id.bottom_navigation);
        m_ApplianceInfo = new ArrayList<>();

        //add menue items
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_history_clock));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_graph_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_person_full));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // initialize fragments
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
//                        fragment = new StatisticsPieFragment();
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new TodaysActivityFragment();
                        break;
                    case 3:
                        fragment = new StatisticsFragment();
                        break;
                    case 4:
                        fragment = new SettingsFragment();
                        break;

                }

                loadFragment(fragment);
            }
        });
        //set home fragment initially selected
        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "I am in " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "I reselected " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    /**
     * This method gets the appliance info List
     * @return
     */
    public ArrayList<ApplianceInfo> getApplianceList(){ return m_ApplianceInfo; }

    /**
     * This method sets the appliance info list
     * @param newInfoArray
     */
    public void setApplianceInfo(ArrayList<ApplianceInfo> newInfoArray){
        this.m_ApplianceInfo = newInfoArray;
    }

}
