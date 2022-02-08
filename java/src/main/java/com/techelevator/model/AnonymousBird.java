package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnonymousBird {

    private String name;
    private String imgUrl;
    private int zipcode;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateSpotted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDate getDateSpotted() {
        return dateSpotted;
    }

    public void setDateSpotted(LocalDate dateSpotted) {
        this.dateSpotted = dateSpotted;
    }
}
