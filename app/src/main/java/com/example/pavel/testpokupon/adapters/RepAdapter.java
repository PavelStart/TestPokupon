package com.example.pavel.testpokupon.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavel.testpokupon.R;
import com.example.pavel.testpokupon.model.RepCard;

import java.util.ArrayList;

/**
 * Created by Pavel on 12.05.2018.
 */

public class RepAdapter extends RecyclerView.Adapter<RepViewHolder> {
    private ArrayList<RepCard> cards;
    private Context context;

    public RepAdapter(Context context, ArrayList<RepCard> cards){
        this.context = context;
        this.cards = cards;
    }

    public void addInfo(ArrayList<RepCard> cards){
        this.cards.addAll(cards);
    }
    @Override
    public RepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rep, parent, false);
        return new RepViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RepViewHolder holder, int position) {
        holder.description.setText(cards.get(position).getDescription());
        holder.name.setText(cards.get(position).getHeader());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
