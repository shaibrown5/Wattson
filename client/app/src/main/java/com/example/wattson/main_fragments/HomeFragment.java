package com.example.wattson.main_fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wattson.Adapter.HomeRecyclerView_Config;
import com.example.wattson.HomeActivity;
import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.R;
import com.example.wattson.utils.FirebaseDBUtils;
import com.example.wattson.InfoClasses.UtilCard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private TextView m_labelCard1;
    private TextView m_priceCard1;
    private ArrayList<ApplianceInfo> m_ApplianceInfo = new ArrayList<>();
    private HomeActivity ac_HomeActivity;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ac_HomeActivity = (HomeActivity) getActivity();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cardGoToArrow1 = (TextView) getView().findViewById(R.id.CardGoToArrow1);
        m_labelCard1 = (TextView) getView().findViewById(R.id.labelCard1);
        m_priceCard1 = (TextView) getView().findViewById(R.id.priceCard1);
        RecyclerView rView = (RecyclerView) getView().findViewById(R.id.recyclerViewHome);
        ArrayList<String> list = new ArrayList<>();

        cardGoToArrow1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int position = 0;
                Fragment utilFrag = new UtilityInfoFragment();

                // save instance of first bundle

                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                utilFrag.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, utilFrag ); // give your fragment container id in first parameter
                //transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        new FirebaseDBUtils("Shoval").getInfo(new FirebaseDBUtils.DataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<ApplianceInfo> info) {
                m_ApplianceInfo.clear();
                m_ApplianceInfo = info;

                bindFirstAppliance(m_ApplianceInfo.get(0));

                // this skips the first appliance, as that jas a static place
                if(m_ApplianceInfo.size() > 1){
                    bindRecycleView(rView);
                }

                ac_HomeActivity.setApplianceInfo(m_ApplianceInfo);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    /**
     * This method binds the appliance info to the static view (only the first)
     * @param i_applianceInfo
     */
    private void bindFirstAppliance(ApplianceInfo i_applianceInfo){
        m_labelCard1.setText(i_applianceInfo.getApplianceName());
        m_priceCard1.setText(i_applianceInfo.getLastReading().getReading());
    }

    private void bindRecycleView(RecyclerView rView){
        List<UtilCard> utilCardList = new ArrayList<>();

        boolean first = true;

        for (ApplianceInfo infoNode: m_ApplianceInfo) {
            if(first){
                first = false;
                continue;
            }

            utilCardList.add(new UtilCard(infoNode.getApplianceName(), infoNode.getLastReading().getReading()));
        }


        new HomeRecyclerView_Config().setConfig(rView, getContext(), utilCardList);
    }


    //
//    public void addInfoToDB(){
//        String info = editTextName.getText().toString().trim();
//
//        // if not empty store to firebase
//        if(!TextUtils.isEmpty(info)){
//            String id = databaseTest.push().getKey();
//            Person per = new Person(info);
//            databaseTest.child("what").child("Person").setValue(per);
//            Toast.makeText(getContext(), "artist added", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(getContext(), "no null infdo", Toast.LENGTH_SHORT).show();
//        }
    }