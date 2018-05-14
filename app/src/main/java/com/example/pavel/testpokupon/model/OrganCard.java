package com.example.pavel.testpokupon.model;

/**
 * Created by Pavel on 12.05.2018.
 */

public class OrganCard {
    private String imagePath;
    private String organName;
    private String organLoc;
    private String organBlog;
    private String repPath;
    private int repNumb;


    public OrganCard(String imagePath,
                   String orgName,
                   String orgLocation,
                   String orgBlog,
                   String repPath,
                   int repNumb){
        this.imagePath = imagePath;
        this.organBlog = orgBlog;
        this.organLoc = orgLocation;
        this.organName = orgName;
        this.repPath = repPath;
        this.repNumb = repNumb;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getOrganBlog() {
        return organBlog;
    }

    public String getOrganLoc() {
        return organLoc;
    }

    public String getOrganName() {
        return organName;
    }

    public String getRepPath() {
        return repPath;
    }

    public int getRepNumb() {
        return repNumb;
    }
}
