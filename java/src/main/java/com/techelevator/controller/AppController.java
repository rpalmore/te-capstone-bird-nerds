package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    ProfileDao dao;

    @RequestMapping(path="/profile", method=RequestMethod.GET)
    public Profile returnProfileByUsername(Principal principal) {
        String username = principal.getName();
        return dao.getProfile(username);
    }

    @RequestMapping(path="/createProfile", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Profile createNewProfile(@RequestBody Profile profile) {
        return dao.addProfile(profile);
    }

    @RequestMapping(path="/editProfile", method=RequestMethod.PUT)
    public void editProfile(@RequestBody Profile updatedProfile) {
        dao.updateProfile(updatedProfile);
    }

    @RequestMapping(path="/deleteProfile/{profileId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProfile(@PathVariable int profileId) {
        dao.deleteProfile(profileId);
    }

}
