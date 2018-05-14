package com.example.pavel.testpokupon.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pavel.testpokupon.R;
import com.example.pavel.testpokupon.adapters.BaseAdapter;
import com.example.pavel.testpokupon.adapters.RepAdapter;
import com.example.pavel.testpokupon.model.RepCard;
import com.example.pavel.testpokupon.presenters.IRepPresenter;
import com.example.pavel.testpokupon.presenters.RepPresenter;

import java.util.ArrayList;

/**
 * Created by Pavel on 12.05.2018.
 */

public class RepActivity extends AppCompatActivity implements IRepActivity {

    private ImageView backBtn;
    private TextView header;
    private RecyclerView recyclerView;
    private RelativeLayout repLayout;
    private int orientation;
    private String orgName;
    private RepAdapter adapter;
    private int orgRepos;
    private static int page = 1;

    private IRepPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep);
        orientation = getResources().getConfiguration().orientation;
        getMainIntent();
        initViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        String headerString = orgName +
                " Repositories(" +
                orgRepos +
                ")";
        header.setText(headerString);
        presenter.onGetInfo(orgName, String.valueOf(page));
    }

    @Override
    public void onShowInfo(ArrayList<RepCard> cards) {
        if (adapter == null){
            adapter = new RepAdapter(this, cards);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.addInfo(cards);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onShowError(String e) {
        Snackbar snackbar = Snackbar.make(repLayout, e, BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.show();
    }


    private void initViews(){
        backBtn = (ImageView) findViewById( R.id.btn_rep_main);
        header = (TextView) findViewById( R.id.tv_header_rep);
        recyclerView = (RecyclerView) findViewById(R.id.rv_repin);
        repLayout = (RelativeLayout) findViewById(R.id.rl_rep);

        GridLayoutManager glm;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            glm = new GridLayoutManager(this, 2);
        } else{
            glm = new GridLayoutManager(this, 1);
        }

        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(new BaseAdapter());
        recyclerView.addOnScrollListener(scrollListener);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenter = new RepPresenter(this, this);
    }

    private void getMainIntent(){
        Intent intent = getIntent();
        orgName = intent.getExtras().getString("orgName");
        orgRepos = intent.getExtras().getInt("orgRepos");
    }


    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            GridLayoutManager glm = (GridLayoutManager) recyclerView.getLayoutManager();
            int lastVisPos = glm.findLastCompletelyVisibleItemPosition();
            if (lastVisPos != RecyclerView.NO_POSITION
                    && lastVisPos == (adapter.getItemCount()-1) ) {
                page++;
                presenter.onGetInfo(orgName, String.valueOf(page));
            }
        }
    };


}
