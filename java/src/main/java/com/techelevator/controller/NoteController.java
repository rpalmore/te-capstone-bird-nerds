package com.techelevator.controller;

import com.techelevator.dao.BirdNoteDao;
import com.techelevator.model.BirdNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class NoteController {

    @Autowired
    BirdNoteDao noteDao;

    @RequestMapping(path="/bird/{birdId}/notes", method = RequestMethod.GET)
    public List<BirdNote> notes(@PathVariable long birdId) {
        return noteDao.getNotes(birdId);
    }

    @RequestMapping(path="/deleteNote/{noteId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable long noteId) {
        noteDao.deleteNote(noteId);
    }

    @RequestMapping(path="/bird/{birdId}/deleteNotes", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBirdsNotes(@PathVariable long birdId) {
        noteDao.deleteAllNotesForBird(birdId);
    }

    @RequestMapping(path="/editNote", method=RequestMethod.PUT)
    public BirdNote editNote(@RequestBody BirdNote note) {
        return noteDao.editNote(note);
    }

    @RequestMapping(path="/newNote", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BirdNote createNote(@RequestBody BirdNote note) {
        return noteDao.addNote(note);
    }


}
