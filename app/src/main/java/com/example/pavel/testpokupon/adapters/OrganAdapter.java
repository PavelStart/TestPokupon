package com.example.pavel.testpokupon.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavel.testpokupon.R;
import com.example.pavel.testpokupon.model.OrganCard;
import com.example.pavel.testpokupon.views.RepActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 12.05.2018.
 */

public class OrganAdapter extends RecyclerView.Adapter<OrganViewHolder>{
    private List<OrganCard> organCards;
    private Context context;

    public OrganAdapter(Context context, ArrayList<OrganCard> organCards){
        this.context = context;
        this.organCards = organCards;
    }

    @Override
    public OrganViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organ, parent, false);
        return new OrganViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrganViewHolder holder, int position) {
        final OrganCard card = organCards.get( holder.getAdapterPosition() );
        holder.organName.setText( card.getOrganName() );
        holder.organBlog.setText( card.getOrganBlog() );
        holder.organLoc.setText( card.getOrganLoc() );

        Picasso.with(context).load( card.getImagePath() )
                .into(holder.organImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepActivity.class);
                intent.putExtra("orgName", card.getOrganName());
                intent.putExtra("orgRepos", card.getRepNumb());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return organCards.size();
    }
}