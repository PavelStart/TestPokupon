package com.example.pavel.testpokupon.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pavel.testpokupon.R;

/**
 * Created by Pavel on 12.05.2018.
 */

public class RepViewHolder extends RecyclerView.ViewHolder {
    protected TextView name;
    protected TextView description;

    public RepViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tv_item_rep_name);
        description = (TextView) itemView.findViewById(R.id.tv_item_rep_descript);
    }

}
