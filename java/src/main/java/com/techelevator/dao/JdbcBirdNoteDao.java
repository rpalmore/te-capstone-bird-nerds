package com.techelevator.dao;

import com.techelevator.model.BirdNote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcBirdNoteDao implements BirdNoteDao {

    private JdbcTemplate template;

    public JdbcBirdNoteDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<BirdNote> getNotes(long birdId) {
        List<BirdNote> notes = new ArrayList<>();

        String sql = "SELECT note_id, notes, date_spotted, num_males, num_females, feeder_type, food_blend " +
                "FROM bird_notes " +
                "WHERE bird_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, birdId);

        while (results.next()) {
            BirdNote note = new BirdNote();
            note.setNoteId(results.getLong("note_id"));
            note.setBirdId(birdId);
            note.setNotes(results.getString("notes"));
            note.setDateSpotted(results.getTimestamp("date_spotted").toLocalDateTime());
            note.setNumMales(results.getInt("num_males"));
            note.setNumFemales(results.getInt("num_females"));
            note.setFeederType(results.getString("feeder_type"));
            note.setFoodBlend(results.getString("food_blend"));

            notes.add(note);
        }

        //sort notes with newest date first (DESC)
        Collections.sort(notes, Collections.reverseOrder());

        return notes;
    }

    @Override
    public void deleteNote(long noteId) {
        String sqlForRemove = "DELETE FROM bird_notes WHERE note_id=?";
        template.update(sqlForRemove, noteId);
    }

    @Override
    public BirdNote editNote(BirdNote note) {
        String sql = "UPDATE bird_notes " +
                "SET bird_id = ?, notes = ? , date_spotted = ?, num_males = ?, num_females = ?, feeder_type = ?, food_blend = ? " +
                "WHERE note_id = ?";
        template.update(
                sql,
                note.getBirdId(),
                note.getNotes(),
                note.getDateSpotted(),
                note.getNumMales(),
                note.getNumFemales(),
                note.getFeederType(),
                note.getFoodBlend(),
                note.getNoteId()
                );
        return note;
    }

    @Override
    public BirdNote addNote(BirdNote note) {
        String sql = "INSERT INTO bird_notes(bird_id, notes, date_spotted, num_males, num_females, feeder_type, food_blend) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING note_id";
        Long noteId = template.queryForObject(
                sql,
                Long.class,
                note.getBirdId(),
                note.getNotes(),
                note.getDateSpotted(),
                note.getNumMales(),
                note.getNumFemales(),
                note.getFeederType(),
                note.getFoodBlend()
                );
        note.setNoteId(noteId);
        return note;
    }

    @Override
    public void deleteAllNotesForBird(long birdId) {
        String sqlForRemove = "DELETE FROM bird_notes WHERE bird_id=?";
        template.update(sqlForRemove, birdId);
    }

}
