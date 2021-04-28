package com.example.wattson;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wattson.Adapter.UtilityAdapter;
import com.example.wattson.utils.SpacingItemDecorator;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UtilityInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UtilityInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UtilityInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UtilityInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UtilityInfoFragment newInstance(String param1, String param2) {
        UtilityInfoFragment fragment = new UtilityInfoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utility_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] values = {"Shai", "Brown", "yo"};
        String[] labels = getResources().getStringArray(R.array.utility_header_list);

        RecyclerView rView = (RecyclerView)getView().findViewById(R.id.recycleViewUtility);
        UtilityAdapter myAdapter = new UtilityAdapter(getContext(), labels, values);
        rView.setAdapter(myAdapter);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        // change this to create a larger margin between the items in recycle view
        SpacingItemDecorator itemDecor = new SpacingItemDecorator(50);
        rView.addItemDecoration(itemDecor);

        TextView t = (TextView) getView().findViewById(R.id.textViewOnUtilityPage);
        //t.setVisibility(View.INVISIBLE);
        t.setVisibility(View.VISIBLE);

    }
}