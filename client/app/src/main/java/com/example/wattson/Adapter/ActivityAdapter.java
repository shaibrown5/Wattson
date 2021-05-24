package com.example.wattson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattson.R;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {

    private List<String> m_startTimes;
    private List<String> m_endTimes;
    private List<String> m_names;
    Context context;

    public ActivityAdapter(Context ct, List<String> names, List<String> startTimes, List<String> endTimes){
        context = ct;
        m_startTimes = startTimes;
        m_names = names;
        m_endTimes = endTimes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_klog_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.startTime.setText(m_startTimes.get(position));
        holder.endTime.setText(m_endTimes.get(position));
        holder.nameOn.setText(String.format("%s ON", m_names.get(position)));
        holder.nameOff.setText(String.format("%s OFF", m_names.get(position)));
    }

    @Override
    public int getItemCount() {
        return m_names.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView startTime;
        private TextView nameOn;
        private TextView nameOff;
        private TextView endTime;

        public MyViewHolder(@NonNull View view){
            super(view);
            startTime = view.findViewById(R.id.textActivityStartTime);
            nameOn = view.findViewById(R.id.textActivityNameOn);
            nameOff = view.findViewById(R.id.textActivityNameOff);
            endTime = view.findViewById(R.id.textActivityEndTime);
        }
    }
}


