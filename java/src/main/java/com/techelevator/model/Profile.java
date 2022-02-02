package com.techelevator.model;

public class Profile {
    private int profileId;
    private String username;
    private String profileImg;
    private int zipCode;
    private String favoriteBird;
    private String skillLevel;
    private String mostCommonBird;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getFavoriteBird() {
        return favoriteBird;
    }

    public void setFavoriteBird(String favoriteBird) {
        this.favoriteBird = favoriteBird;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getMostCommonBird() {
        return mostCommonBird;
    }

    public void setMostCommonBird(String mostCommonBird) {
        this.mostCommonBird = mostCommonBird;
    }
}
