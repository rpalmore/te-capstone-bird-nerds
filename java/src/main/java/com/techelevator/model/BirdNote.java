package com.techelevator.model;

import java.time.LocalDateTime;

public class BirdNote implements Comparable<BirdNote> {

    private long noteId;
    private long birdId;
    private String notes;
    private LocalDateTime dateSpotted;
    private int numMales;
    private int numFemales;
    private String feederType;
    private String foodBlend;

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getBirdId() {
        return birdId;
    }

    public void setBirdId(long birdId) {
        this.birdId = birdId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDateSpotted() {
        return dateSpotted;
    }

    public void setDateSpotted(LocalDateTime dateSpotted) {
        this.dateSpotted = dateSpotted;
    }

    public int getNumMales() {
        return numMales;
    }

    public void setNumMales(int numMales) {
        this.numMales = numMales;
    }

    public int getNumFemales() {
        return numFemales;
    }

    public void setNumFemales(int numFemales) {
        this.numFemales = numFemales;
    }

    public String getFeederType() {
        return feederType;
    }

    public void setFeederType(String feederType) {
        this.feederType = feederType;
    }

    public String getFoodBlend() {
        return foodBlend;
    }

    public void setFoodBlend(String foodBlend) {
        this.foodBlend = foodBlend;
    }

    /**
     * Compares two TransactionRecords based on their date spotted
     * @param o the other TransactionRecord to be compared against
     * @return int > 0 if this object has a chronologically later date spotted than o,
     *          int == 0 if this object and o have the same date spotted,
     *          int < - if this object has a chronologically earlier date spotted than o
     */
    @Override
    public int compareTo(BirdNote o) {
        return this.getDateSpotted().compareTo(o.getDateSpotted());
    }
}
