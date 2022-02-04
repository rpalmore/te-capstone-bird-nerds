package com.techelevator.model;

import java.time.LocalDateTime;

public class AnonymousBird {

    private String name;
    private int zipcode;
    private LocalDateTime dateSpotted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDateTime getDateSpotted() {
        return dateSpotted;
    }

    public void setDateSpotted(LocalDateTime dateSpotted) {
        this.dateSpotted = dateSpotted;
    }
}
