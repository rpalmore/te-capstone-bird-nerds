package com.techelevator.dao;

import com.techelevator.model.Bird;

import java.util.List;

public interface BirdDao {
    List<Bird> getBirdsInList(long listId);

    Bird getBirdById(long birdId);

    void deleteBird(long birdId);

    void deleteBirdsFromList(long listId);

    Bird updateBird(Bird bird);
}
