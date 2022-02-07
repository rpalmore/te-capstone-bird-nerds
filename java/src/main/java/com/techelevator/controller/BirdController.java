package com.techelevator.controller;

import com.techelevator.dao.BirdDao;
import com.techelevator.model.AnonymousBird;
import com.techelevator.model.Bird;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BirdController {

    @Autowired
    BirdDao birdDao;

    @RequestMapping(path="/lists/{listId}/birds", method=RequestMethod.GET)
    public List<Bird> getListOfBirds(@PathVariable long listId) {
        return birdDao.getBirdsInList(listId);
    }

    @RequestMapping(path="/bird/{birdId}", method=RequestMethod.GET)
    public Bird getBird(@PathVariable long birdId) {
        return birdDao.getBirdById(birdId);
    }

    @RequestMapping(path="/lists/{listId}/addBird", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Bird addBird(@RequestBody Bird newBird, @PathVariable long listId) {
        return birdDao.createBird(newBird, listId);
    }

    @RequestMapping(path="/updateBird", method=RequestMethod.PUT)
    public Bird updateBird(@RequestBody Bird editedBird) {
        return birdDao.updateBird(editedBird);
    }

    @RequestMapping(path="/deleteBird/{birdId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBird(@PathVariable long birdId) {
        birdDao.deleteBird(birdId);
    }

    @RequestMapping(path="/birds/{zipcode}", method=RequestMethod.GET)
    @PreAuthorize("permitAll")
    public List<AnonymousBird> getBirdByZip(@PathVariable int zipcode) {
        return birdDao.getBirdsByZip(zipcode);
    }
}
