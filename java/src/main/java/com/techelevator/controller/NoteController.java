package com.techelevator.controller;

import com.techelevator.dao.BirdNoteDao;
import com.techelevator.model.BirdNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class NoteController {

    @Autowired
    BirdNoteDao noteDao;

    @RequestMapping(path="/bird/{birdId}/notes", method = RequestMethod.GET)
    public List<BirdNote> notes(@PathVariable long birdId) {
        return noteDao.getNotes(birdId);
    }

    @RequestMapping(path="/delete/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable long noteId) {
        noteDao.deleteNote(noteId);
    }

    @RequestMapping(path="/bird/{birdId}/delete-notes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBirdsNotes(@PathVariable long birdId) {
        noteDao.deleteAllNotesForBird(birdId);
    }


}
