package com.example.pavel.testpokupon.views;

import com.example.pavel.testpokupon.model.RepCard;

import java.util.ArrayList;

/**
 * Created by Pavel on 12.05.2018.
 */

public interface IRepActivity {
    void onShowInfo(ArrayList<RepCard> cards);
    void onShowError(String e);

}