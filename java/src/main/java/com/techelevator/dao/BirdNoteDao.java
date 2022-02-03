package com.techelevator.dao;

import com.techelevator.model.BirdNote;

import java.util.List;

public interface BirdNoteDao {
    List<BirdNote> getNotes(long birdId);

    void deleteNote(long noteId);

    BirdNote editNote(BirdNote note);

    BirdNote addNote(BirdNote note);

    void deleteAllNotesForBird(long birdId);
}
