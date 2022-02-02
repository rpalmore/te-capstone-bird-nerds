package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    Profile getProfile(String username);

    Profile addProfile(Profile newProfile);

    void deleteProfile(int profileId);

    void updateProfile(Profile updatedProfile);
}
