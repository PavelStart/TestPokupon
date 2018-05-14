package com.example.pavel.testpokupon.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavel.testpokupon.R;

/**
 * Created by Pavel on 12.05.2018.
 */

public class OrganViewHolder extends RecyclerView.ViewHolder {

    protected ImageView organImage;
    protected TextView organName;
    protected TextView organLoc;
    protected TextView organBlog;


    public OrganViewHolder(View itemView) {
        super(itemView);
        organName = (TextView) itemView.findViewById( R.id.tv_item_organ_name );
        organLoc = (TextView) itemView.findViewById( R.id.tv_item_organ_loc );
        organBlog = (TextView) itemView.findViewById( R.id.tv_item_organ_blog );
        organImage = (ImageView) itemView.findViewById(R.id.iv_item_organ);

    }

}