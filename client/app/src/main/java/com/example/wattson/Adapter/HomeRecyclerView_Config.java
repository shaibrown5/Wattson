package com.example.wattson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattson.R;
import com.example.wattson.InfoClasses.UtilCard;

import java.util.List;
public class HomeRecyclerView_Config{

    private Context m_Context;
    private HomeAdapter m_HomeAdapter;

    public void setConfig(RecyclerView rView, Context context, List<UtilCard> cards){
        m_Context = context;
        m_HomeAdapter = new HomeAdapter(context, cards);

        rView.setLayoutManager(new GridLayoutManager(context, 2));
        rView.setAdapter(m_HomeAdapter);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView labelText;
        TextView priceText;

        public MyViewHolder(@NonNull View view){
            super(view);
            labelText = (TextView) view.findViewById(R.id.labelCard);
            priceText = (TextView) view.findViewById(R.id.priceCard);
        }
    }
    class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<UtilCard> m_Cards;
        private Context m_Context;

        public HomeAdapter(Context ct, List<UtilCard> i_cards){
            m_Context = ct;
            m_Cards = i_cards;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(m_Context);
            View view = inflater.inflate(R.layout.home_page_card, parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.labelText.setText(m_Cards.get(position).getLabel());
            holder.priceText.setText(m_Cards.get(position).getPrice());
        }

        @Override
        public int getItemCount() {
            return m_Cards.size();
        }
    }

}

