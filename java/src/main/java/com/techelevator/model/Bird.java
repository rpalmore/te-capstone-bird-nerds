package com.techelevator.model;

import java.util.List;

public class Bird {

    private String birdName;
    private String birdImg;
    private int numSightings;
    private int zipcode;
    private List<BirdNotes> birdNotes;

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

    public List<BirdNotes> getBirdNotes() {
        return birdNotes;
    }

    public void setBirdNotes(List<BirdNotes> birdNotes) {
        this.birdNotes = birdNotes;
    }
}
