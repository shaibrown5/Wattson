package com.example.wattson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattson.R;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.MyViewHolder> {

    private String[] m_label;
    private String[] m_values;
    Context context;

    public SettingsAdapter(Context ct, String[] label, String[] values){
        context = ct;
        m_label = label;
        m_values = values;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.setting_plain_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.labelText.setText(m_label[position]);
        if(!m_values[position].equals("-1")){
            holder.valueText.setText(m_values[position]);
        }
    }

    @Override
    public int getItemCount() {
        return m_label.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView labelText;
        private TextView valueText;

        public MyViewHolder(@NonNull View view){
            super(view);
            labelText = view.findViewById(R.id.textViewSettingLabel);
            valueText = view.findViewById(R.id.textViewSettingSecondary);
        }
    }
}

