package com.example.wattson.main_fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wattson.HomeActivity;
import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.R;
import com.example.wattson.utils.FirebaseDBUtils;
import com.example.wattson.utils.LoadingDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // the cards of utils
    private MaterialCardView[] cd_cardslist;
    // the icons of the cards
    private MaterialTextView[] img_iconCardList;
    // the name of the utility
    private MaterialTextView[] txt_nameCardList;
    // the on symbol cards
    private MaterialCardView[] cd_onIndicatorList;

    private MaterialTextView txt_todaysCost;
    private MaterialTextView txt_monthlyEst;
    private ArrayList<ApplianceInfo> m_ApplianceInfo = new ArrayList<>();
    private HomeActivity ac_HomeActivity;
    private final String m_userName = "Shoval";


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
        // this is the loading bar
        LoadingDialog loadingDialog = new LoadingDialog(HomeFragment.this);
        if (ac_HomeActivity.getIsFirstTime()){
            loadingDialog.startLoadingDialog();
        }


        txt_todaysCost = (MaterialTextView) getView().findViewById(R.id.textViewUTodaysAmmount);
        txt_monthlyEst = (MaterialTextView) getView().findViewById(R.id.textViewEstAmmount);

        // cards of the items
        cd_cardslist = new MaterialCardView[]{(MaterialCardView) getView().findViewById(R.id.Card1),
                (MaterialCardView) getView().findViewById(R.id.Card2),
                (MaterialCardView) getView().findViewById(R.id.Card3),
                (MaterialCardView) getView().findViewById(R.id.Card4),
                (MaterialCardView) getView().findViewById(R.id.Card5)};


        // image icons
        img_iconCardList = new MaterialTextView[]{(MaterialTextView) getView().findViewById(R.id.utilSymbolCard1),
                (MaterialTextView) getView().findViewById(R.id.utilSymbolCard2),
                (MaterialTextView) getView().findViewById(R.id.utilSymbolCard3),
                (MaterialTextView) getView().findViewById(R.id.utilSymbolCard4),
                (MaterialTextView) getView().findViewById(R.id.utilSymbolCard5)};

        // the names of the utils
        txt_nameCardList = new MaterialTextView[]{(MaterialTextView) getView().findViewById(R.id.utilNameCard1),
                (MaterialTextView) getView().findViewById(R.id.utilNameCard2),
                (MaterialTextView) getView().findViewById(R.id.utilNameCard3),
                (MaterialTextView) getView().findViewById(R.id.utilNameCard4),
                (MaterialTextView) getView().findViewById(R.id.utilNameCard5)};

        //on Symbol cards
        cd_onIndicatorList = new MaterialCardView[]{(MaterialCardView) getView().findViewById(R.id.onIndicatorCARDCard1),
                (MaterialCardView) getView().findViewById(R.id.onIndicatorCARDCard2),
                (MaterialCardView) getView().findViewById(R.id.onIndicatorCARDCard3),
                (MaterialCardView) getView().findViewById(R.id.onIndicatorCARDCard4),
                (MaterialCardView) getView().findViewById(R.id.onIndicatorCARDCard5)};

        setAllInvisible();


        new FirebaseDBUtils(m_userName).getInfo(new FirebaseDBUtils.DataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<ApplianceInfo> info) {
                m_ApplianceInfo.clear();
                m_ApplianceInfo = info;

                setLayout();
                try{
                    loadingDialog.dismissDialog();
                    ac_HomeActivity.setIsFirstTime();
                }catch (NullPointerException n){
                    Log.e("[LOADING]", "loading was dismissed");
                }


                ac_HomeActivity.setApplianceInfo(m_ApplianceInfo);
                txt_todaysCost.setText(String.format("%.3f", getDailyCost()));
                txt_monthlyEst.setText(String.format("%.3f", getDailyCost()*30));
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
     * This method sets up the layout according to the infor received from the db
     */
    private void setLayout() {
        int[] symbolsArray = new int[]{R.drawable.ic_air_conditioner, R.drawable.ic_oven, R.drawable.ic_dish_washer, R.drawable.ic_electric_kettle, R.drawable.ic_washing_machine};
        int[] blackSymbolarray = new int[]{R.drawable.ic_black_ac, R.drawable.ic_black_oven, R.drawable.ic_black_dish, R.drawable.ic_black_kettle, R.drawable.ic_black_washing};

        for (int i = 0; i < m_ApplianceInfo.size(); i++) {
            ApplianceInfo currAppliance = m_ApplianceInfo.get(i);
            cd_cardslist[i].setVisibility(View.VISIBLE);
            txt_nameCardList[i].setText(currAppliance.getApplianceName());
            img_iconCardList[i].setCompoundDrawablesWithIntrinsicBounds(symbolsArray[i],0,0,0);
            currAppliance.setItemSymbol(blackSymbolarray[i]);

            cd_onIndicatorList[i].setVisibility(View.INVISIBLE);
            if (currAppliance.isCurrentlyOn()) {
                cd_onIndicatorList[i].setVisibility(View.VISIBLE);
            }

            int pos = i;
            cd_cardslist[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment utilFrag = new UtilityInfoFragment();

                    // save instance of first bundle

                    Bundle bundle = new Bundle();
                    bundle.putInt("pos", pos);
                    utilFrag.setArguments(bundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, utilFrag); // give your fragment container id in first parameter
                    //transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                    transaction.commit();
                }

            });
        }
    }

    /**
     * sets all the page to invisible until data is received
     */
    private void setAllInvisible(){
        for (int i = 0; i < 5 ; i++) {
            cd_cardslist[i].setVisibility(View.INVISIBLE);
            cd_onIndicatorList[i].setVisibility(View.INVISIBLE);
        }
    }

    private double getDailyCost(){
        double dailyCost = 0;

        for (ApplianceInfo currApp: m_ApplianceInfo) {
            dailyCost += currApp.getDailyPrice();
        }

        return dailyCost;
    }

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