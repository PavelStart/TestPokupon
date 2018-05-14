package com.example.pavel.testpokupon.model;

/**
 * Created by Pavel on 12.05.2018.
 */

public class RepCard {
    private String header;
    private String description;

    public RepCard(String header, String description){
        this.description = description;
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public String getHeader() {
        return header;
    }
}
