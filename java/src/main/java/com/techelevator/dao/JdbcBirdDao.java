package com.techelevator.dao;

import com.techelevator.model.Bird;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBirdDao implements BirdDao{

    @Autowired
    private BirdNoteDao noteDao;
    private JdbcTemplate template;

    public JdbcBirdDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Bird> getBirdsInList(long listId) {

        String sql = "SELECT bird_id, bird_name, bird_img, num_sightings, zipcode " +
                "FROM birds " +
                "WHERE list_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, listId);

        List<Bird> birds = new ArrayList<>();

        while(results.next()) {
            Bird bird = makeBirdFromSqlRowSet(results);
            birds.add(bird);
        }

        return birds;
    }

    @Override
    public Bird getBirdById(long birdId) {

        String sql = "SELECT bird_id, bird_name, bird_img, num_sightings, zipcode " +
                "FROM birds " +
                "WHERE bird_id = ?";
        SqlRowSet result = template.queryForRowSet(sql, birdId);

        Bird bird = new Bird();
        if(result.next()) {
            bird = makeBirdFromSqlRowSet(result);
        }
        return bird;
    }

    @Override
    public void deleteBird(long birdId) {

        String sql = "DELETE FROM bird WHERE bird_id = ?";
        template.update(sql, birdId);
        noteDao.deleteAllNotesForBird(birdId);
    }

    @Override
    public void deleteBirdsFromList(long listId) {
        //get all birds, then call deleteBird so the notes are also deleted
        String sql = "SELECT bird_id FROM birds WHERE list_id=?";
        SqlRowSet results = template.queryForRowSet(sql, listId);
        while(results.next()) {
            deleteBird(results.getLong("bird_id"));
        }

    }

    @Override
    public Bird updateBird(Bird bird) {
        String sql = "UPDATE birds " +
                "SET bird_name = ?, bird_img = ?, num_sightings = ?, zipcode = ? " +
                "WHERE bird_id = ?";
        template.update(
                sql,
                bird.getBirdName(),
                bird.getBirdImg(),
                bird.getNumSightings(),
                bird.getZipcode(),
                bird.getBirdID()
        );
        return bird;
    }

    public List<Bird> getBirdsByZip(int zipcode) {
        List<Bird> birds = new ArrayList<>();


        return birds;
    }

    private Bird makeBirdFromSqlRowSet(SqlRowSet result) {
        Bird bird = new Bird();
        bird.setBirdID(result.getLong("bird_id"));
        bird.setBirdName(result.getString("bird_name"));
        bird.setBirdImg(result.getString("bird_img"));
        bird.setNumSightings(result.getInt("num_sightings"));
        bird.setZipcode(result.getInt("zipcode"));
        return bird;
    }
}
