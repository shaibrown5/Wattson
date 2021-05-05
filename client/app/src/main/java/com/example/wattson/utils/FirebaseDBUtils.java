package com.example.wattson.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wattson.InfoClasses.ApplianceInfo;
import com.example.wattson.InfoClasses.IndividualReading;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDBUtils {
    DatabaseReference databaseTest;
    private final String TAG = "[FIRBASE_DB_HELPER]";

    public interface DataStatus{
        void DataIsLoaded(List<ApplianceInfo> applianceInfo);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FirebaseDBUtils(String i_UserID){
        databaseTest = FirebaseDatabase.getInstance().getReference().child(i_UserID);
        Log.d(TAG, "in constructor");
    }

    public void getInfo(final DataStatus i_dataStatus){
        Log.d(TAG, "in getInfo");
        databaseTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){

                List<ApplianceInfo> applianceInfoList = new ArrayList<>();
                List<IndividualReading> individualReadingList = new ArrayList<>();

                // gets the appliance info
                for (DataSnapshot keyNode: snapshot.getChildren()) {
                    String applianceName = keyNode.getKey();

                    // gets the node info
                    for (DataSnapshot childNode: keyNode.getChildren()) {

                        // extract info from info
                        for (DataSnapshot infoNode: keyNode.getChildren()) {
                            int count = 0;
                            String timestamp = "";
                            String power = "";

                            for(DataSnapshot infoChildNode: infoNode.getChildren()){
                                if (count == 0){
                                    timestamp = infoChildNode.getValue().toString();
                                    count ++;
                                }
                                else{
                                    power = infoChildNode.getValue().toString();
                                }
                            }
                            individualReadingList.add(new IndividualReading(timestamp, power));
                        }
                    }

                    applianceInfoList.add(new ApplianceInfo(applianceName, individualReadingList));
                }

                i_dataStatus.DataIsLoaded(applianceInfoList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    private String readInfo(){
//
//    }


//    public void addInfoToDB(String i_info) {
//        String info = editTextName.getText().toString().trim();
//
//        // if not empty store to firebase
//        if (!TextUtils.isEmpty(info)) {
//            String id = databaseTest.push().getKey();
//            Person per = new Person(info);
//            databaseTest.child("what").child("Person").setValue(per);
//            Toast.makeText(getContext(), "artist added", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), "no null infdo", Toast.LENGTH_SHORT).show();
//        }
//    }
}
