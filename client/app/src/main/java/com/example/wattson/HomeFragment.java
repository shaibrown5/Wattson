package com.example.wattson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wattson.Adapter.HomeRecyclerView_Config;
import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.utils.FirebaseDBUtils;
import com.example.wattson.InfoClasses.UtilCard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView m_labelCard1;
    TextView m_priceCard1;
    Button addButton;
    DatabaseReference databaseTest;
    List<ApplianceInfo> m_ApplianceInfo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        databaseTest = FirebaseDatabase.getInstance().getReference().child("UsersData").child("Node1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        m_ApplianceInfo = new ArrayList<>();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView t = (TextView) getView().findViewById(R.id.CardGoToArrow1);
        m_labelCard1 = (TextView) getView().findViewById(R.id.labelCard1);
        m_priceCard1 = (TextView) getView().findViewById(R.id.priceCard1);
        ArrayList<String> list = new ArrayList<>();

        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Fragment someFragment = new UtilityInfoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, someFragment ); // give your fragment container id in first parameter
                //transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        RecyclerView rView = (RecyclerView) getView().findViewById(R.id.recyclerViewHome);
        new FirebaseDBUtils("UsersData").getInfo(new FirebaseDBUtils.DataStatus() {
            @Override
            public void DataIsLoaded(List<ApplianceInfo> info) {
                m_ApplianceInfo.clear();
                m_ApplianceInfo = info;

                List<UtilCard> utilCardList = new ArrayList<>();

                for (ApplianceInfo infoNode: m_ApplianceInfo) {
                    utilCardList.add(new UtilCard(infoNode.getApplianceName(), infoNode.getLastReading().getReading()));
                }


                new HomeRecyclerView_Config().setConfig(rView, getContext(), utilCardList);

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


//    private class Person{
//        String name;
//
//        public Person(String name){
//            this.name = name;
//        }
//
//        public String getPersonName(){
//            return name;
//        }
//    }
//}

