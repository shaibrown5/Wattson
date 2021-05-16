package com.example.wattson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattson.R;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {

    private String[] m_startTimes;
    private String[] m_endTimes;
    private String[] m_names;
    Context context;

    public ActivityAdapter(Context ct, String[] names, String[] startTimes, String[] endTimes){
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
        holder.startTime.setText(m_startTimes[position]);
        holder.endTime.setText(m_endTimes[position]);
        holder.nameOn.setText(m_names[position] + " ON");
        holder.nameOff.setText(m_names[position] + " OFF");
    }

    @Override
    public int getItemCount() {
        return m_names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
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


