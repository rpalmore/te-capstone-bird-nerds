package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated")
public class ListController {

    @Autowired
    UserDao userDao;

    @Autowired
    ListDao listDao;

    @RequestMapping(path="/lists", method= RequestMethod.GET)
    public List<BirdList> getAllLists(Principal principal) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        return listDao.getLists(userId);
    }

    @RequestMapping(path="/lists/{listId}", method=RequestMethod.GET)
    public BirdList getList (@PathVariable int listId) {
        return listDao.getList(listId);
    }

    @RequestMapping(path="/createList", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BirdList createList(@RequestBody BirdList newList) {
        return listDao.addList(newList);
    }

    @RequestMapping(path="/editList", method=RequestMethod.PUT)
    public BirdList editList(@RequestBody BirdList editedList) {
        return listDao.updateList(editedList);
    }

    @RequestMapping(path="/deleteList/{listId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeList(@PathVariable int listId) {
        listDao.deleteList(listId);
    }
}
