package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bird {

    private long birdID;
    private String birdName;
    private String birdImg;
    private int numSightings;
    private int zipcode;
    private LocalDateTime mostRecentSighting;

    public long getBirdID() {
        return birdID;
    }

    public void setBirdID(long birdID) {
        this.birdID = birdID;
    }

    public String getBirdName() {
        return birdName;
    }

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }

    public String getBirdImg() {
        return birdImg;
    }

    public void setBirdImg(String birdImg) {
        this.birdImg = birdImg;
    }

    public int getNumSightings() {
        return numSightings;
    }

    public void setNumSightings(int numSightings) {
        this.numSightings = numSightings;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDateTime getMostRecentSighting() {
        return mostRecentSighting;
    }

    public void setMostRecentSighting(LocalDateTime mostRecentSighting) {
        this.mostRecentSighting = mostRecentSighting;
    }
}
