package com.techelevator.controller;

import com.techelevator.dao.BirdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BirdController {

    @Autowired
    BirdDao birdDao;

    // path /lists/{listId}/birds - get all birds in a list
    // /bird/{birdId} - get a bird
    // /deleteBird/{birdId}
    // deleteBirdsFromLIst only called in ListDao
    // /updateBird
    //


}
