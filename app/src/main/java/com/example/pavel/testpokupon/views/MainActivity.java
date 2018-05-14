package com.example.pavel.testpokupon.views;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pavel.testpokupon.R;
import com.example.pavel.testpokupon.adapters.OrganAdapter;
import com.example.pavel.testpokupon.model.Config;
import com.example.pavel.testpokupon.model.OrganCard;
import com.example.pavel.testpokupon.presenters.IPresenter;
import com.example.pavel.testpokupon.presenters.MainPresenter;
import com.example.pavel.testpokupon.utilits.NetState;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IActivity{

    private RelativeLayout mainLayout;
    private EditText input;
    private RecyclerView recyclerView;


    private IPresenter presenter;
    private ArrayList<OrganCard> organizations;
    private int orientationMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientationMode = getResources().getConfiguration().orientation;

        initViews();
        setEditText();
        setRecyclerView();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    public void showInfo(OrganCard card) {
        organizations = new ArrayList<>();
        organizations.add(card);

        recyclerView.setAdapter(new OrganAdapter(this, organizations));
        recyclerView.getAdapter().notifyDataSetChanged();



    }

    @Override
    public void showError(String e) {
        Snackbar snackbar =
                Snackbar.make(mainLayout, e, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();
    }


    private TextWatcher inputWatcher = new TextWatcher() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable;
        int beforeCh;
        int afterCh;
        long timeStart, timeEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            beforeCh = s.length();
            timeStart = System.currentTimeMillis();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            timeEnd = System.currentTimeMillis();
            afterCh = s.length();
            final String string = s.toString();

            if (( s.length() >= Config.ET_MIN && afterCh > beforeCh )
                    || (s.length() >= Config.ET_MIN && (timeEnd - timeStart) >=700 ) ) {
                if (NetState.isConnected(MainActivity.this)){
                    handler.removeCallbacks(runnable);
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            presenter.onGetInfo(string);
                        }
                    };
                    handler.postDelayed(runnable, 700);
                } else {
                    showError("No internet connection!");
                }
            }
        }
    };

    private void initViews(){
        presenter = new MainPresenter(this, this);

        input = (EditText) findViewById( R.id.et_input_main );
        recyclerView = (RecyclerView) findViewById( R.id.rv_main );
        mainLayout = (RelativeLayout) findViewById(R.id.rl_main);



    }

    private void setEditText(){
        input.addTextChangedListener(inputWatcher);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                    input.clearFocus();
                    return true;
                }
                return false;
            }
        });

    }

    private void setRecyclerView(){
        GridLayoutManager glm;

        if (orientationMode == Configuration.ORIENTATION_PORTRAIT){
            glm = new GridLayoutManager(this, 1);
        }
        else {
            glm = new GridLayoutManager(this, 2);
        }
        recyclerView.setLayoutManager(glm);
    }




}
