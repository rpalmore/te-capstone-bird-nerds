package com.techelevator.dao;

import com.techelevator.model.BirdList;

import java.util.List;

public interface ListDao {

    List<BirdList> getLists(int userId);

    BirdList getList(int listId);

    BirdList addList(BirdList list);

    BirdList updateList(BirdList list);

    void deleteList(int listId);
}
