package com.techelevator.dao;

import com.techelevator.model.AnonymousBird;
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

            String getDateSQL = "SELECT MAX(date_spotted) AS recent_sighting\n" +
                    "FROM bird_notes\n" +
                    "WHERE bird_id = ?";
            SqlRowSet dateResult = template.queryForRowSet(getDateSQL, bird.getBirdID());


            if (dateResult.next()) {
                if (dateResult.getTimestamp("recent_sighting") != null) bird.setMostRecentSighting(dateResult.getDate("recent_sighting").toLocalDate());
                else bird.setMostRecentSighting(null);
            }

            birds.add(bird);
        }

        return birds;
    }

    @Override
    public Bird getBirdById(long birdId) {

        String sql = "SELECT birds.bird_id, bird_name, bird_img, num_sightings, zipcode\n" +
                "FROM birds\n" +
                "WHERE birds.bird_id = ?\n";
        SqlRowSet result = template.queryForRowSet(sql, birdId);

        String getDateSQL = "SELECT MAX(date_spotted) AS recent_sighting\n" +
                "FROM bird_notes\n" +
                "WHERE bird_id = ?";
        SqlRowSet dateResult = template.queryForRowSet(getDateSQL, birdId);

        Bird bird = new Bird();
        if(result.next()) {
            bird = makeBirdFromSqlRowSet(result);
            if (dateResult.next()) {
                if (dateResult.getDate("recent_sighting") != null) bird.setMostRecentSighting(dateResult.getDate("recent_sighting").toLocalDate());
                else bird.setMostRecentSighting(null);
            }
        }
        return bird;
    }

    @Override
    public void deleteBird(long birdId) {

        String sql = "DELETE FROM birds WHERE bird_id = ?";
        noteDao.deleteAllNotesForBird(birdId);
        template.update(sql, birdId);
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

    //listId can be sent in as a path variable, bird as request body
    public Bird createBird(Bird bird, long listId) {
        String sql = "INSERT INTO birds(bird_name, bird_img, num_sightings, zipcode, list_id) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING bird_id";
        long birdId = template.queryForObject(
                sql,
                Long.class,
                bird.getBirdName(),
                bird.getBirdImg(),
                bird.getNumSightings(),
                bird.getZipcode(),
                listId
        );
        bird.setBirdID(birdId);
        return bird;
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

    @Override
    public List<AnonymousBird> getBirdsByZip(int zipcode) {
        List<AnonymousBird> birds = new ArrayList<>();

        String sql = "SELECT bird_name, bird_img, zipcode, date_spotted\n" +
                "FROM birds\n" +
                "JOIN bird_notes ON birds.bird_id=bird_notes.bird_id\n" +
                "WHERE zipcode = ?\n" +
                "ORDER BY date_spotted DESC\n" +
                "LIMIT 10";
        SqlRowSet results = template.queryForRowSet(sql, zipcode);

        while(results.next()) {
            AnonymousBird bird = makeAnonBirdFromSqlRowSet(results);
            birds.add(bird);
        }

        return birds;
    }

    @Override
    public AnonymousBird getRandomBird() {
        String countSql = "SELECT COUNT(*) FROM birds";
        SqlRowSet result = template.queryForRowSet(countSql);

        int numBirds = 0;
        if (result.next()) {
            numBirds = result.getInt("count");
        }

        int birdId = (int)(Math.random() * numBirds);
        String birdSql = "SELECT bird_name, bird_img, zipcode, date_spotted " +
                "FROM birds " +
                "JOIN bird_notes ON birds.bird_id=bird_notes.bird_id " +
                "WHERE bird_id = ? " +
                "ORDER BY date_spotted DESC " +
                "LIMIT 1";
        result = template.queryForRowSet(birdSql, birdId);

        AnonymousBird randomBird = null;
        if (result.next()) {
            randomBird = makeAnonBirdFromSqlRowSet(result);
        }
        return randomBird;
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

    private AnonymousBird makeAnonBirdFromSqlRowSet(SqlRowSet result) {
        AnonymousBird bird = new AnonymousBird();
        bird.setName(result.getString("bird_name"));
        bird.setImgUrl(result.getString("bird_img"));
        bird.setZipcode(result.getInt("zipcode"));
        if (result.getDate("date_spotted") != null) {
            bird.setDateSpotted(result.getDate("date_spotted").toLocalDate());
        } else {
            bird.setDateSpotted(null);
        }
        return bird;
    }
}
