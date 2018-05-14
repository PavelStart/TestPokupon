package com.example.pavel.testpokupon.views;

import com.example.pavel.testpokupon.model.OrganCard;

/**
 * Created by Pavel on 12.05.2018.
 */

public interface IActivity {
    void showInfo(OrganCard card);
    void showError(String e);

}