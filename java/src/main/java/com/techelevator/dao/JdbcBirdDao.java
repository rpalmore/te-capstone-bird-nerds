package com.techelevator.dao;

import com.techelevator.model.Bird;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBirdDao {

    private JdbcTemplate template;

    public JdbcBirdDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

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
