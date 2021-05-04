package com.example.wattson.utils;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDBUtils {
    DatabaseReference databaseTest;
    private String m_AppliancName = null;
    private List<String> m_info = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<String> info, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FirebaseDBUtils(String i_UserID, String i_ApplianceName){
        databaseTest = FirebaseDatabase.getInstance().getReference().child(i_UserID).child(i_ApplianceName);
        this.m_AppliancName = i_ApplianceName;
    }

    public void getInfo(final DataStatus i_dataStatus){
        databaseTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode: snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    m_info.add(keyNode.getValue().toString());
                    Log.d("[GET INFOR FROM DB]", keyNode.getValue().toString());
                }

                i_dataStatus.DataIsLoaded(m_info, keys);
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
