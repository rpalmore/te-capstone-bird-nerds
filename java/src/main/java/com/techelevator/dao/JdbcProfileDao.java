package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcProfileDao implements ProfileDao {

    private JdbcTemplate template;

    public JdbcProfileDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public Profile getProfile(String username) {

        String sql = "SELECT username, profile_img, zip_code, favorite_bird, skill_level, most_common_bird " +
                "FROM profiles " +
                "JOIN users ON users.user_id = profiles.user_id " +
                "WHERE username = ?";
        SqlRowSet result = template.queryForRowSet(sql, username);

        Profile profile = null;
        if (result.next()) {
            profile = new Profile();
            profile.setUsername(username);
            profile.setProfileImg(result.getString("profile_img"));
            profile.setZipCode(result.getInt("zip_code"));
            profile.setFavoriteBird(result.getString("favorite_bird"));
            profile.setSkillLevel(result.getString("skill_level"));
            profile.setMostCommonBird(result.getString("most_common_bird"));
        }
        return profile;
    }

    @Override
    public Profile addProfile(Profile newProfile) {
        String sql = "INSERT INTO profiles(user_id, profile_img, zip_code, favorite_bird, skill_level, most_common_bird) " +
                "VALUES ((SELECT user_id FROM users WHERE username = ?), ?, ?, ?, ?, ?) RETURNING user_id";

        int newUser = template.queryForObject(sql, Integer.class, newProfile.getUsername(), newProfile.getProfileImg(),
                newProfile.getZipCode(), newProfile.getFavoriteBird(), newProfile.getSkillLevel(), newProfile.getMostCommonBird());

        String sqlReceipt = "SELECT username FROM users WHERE user_id = ?";
        SqlRowSet receipt = template.queryForRowSet(sqlReceipt, newUser);
        String username = null;
        if (receipt.next()) {
            username = receipt.getString("username");
        }
        return getProfile(username);
    }

    @Override
    public void updateProfile(Profile updatedProfile) {
        String sql = "UPDATE profiles " +
                "SET profile_img = ?, zip_code = ?, favorite_bird = ?, skill_level = ?, most_common_bird = ? " +
                "WHERE user_id IN (SELECT user_id FROM users WHERE username = ?)";

        template.update(sql, updatedProfile.getProfileImg(), updatedProfile.getZipCode(), updatedProfile.getFavoriteBird(),
                updatedProfile.getSkillLevel(), updatedProfile.getMostCommonBird(), updatedProfile.getUsername());
    }

    @Override
    public void deleteProfile(int profileId) {
        String sql = "DELETE FROM profiles WHERE profile_id = ?";
        template.update(sql, profileId);
    }
}
