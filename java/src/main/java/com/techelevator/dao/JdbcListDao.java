package com.techelevator.dao;

import com.techelevator.model.BirdList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcListDao implements ListDao {

    private JdbcTemplate template;

    public JdbcListDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<BirdList> getLists(int userId) {

        List<BirdList> lists = new ArrayList<>();

        String sql = "SELECT list_id, list_name, user_id " +
                "FROM lists WHERE user_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, userId);

        while (results.next()) {
            BirdList list = new BirdList();
            list.setListId(results.getInt("list_id"));
            list.setListName(results.getString("list_name"));
            list.setUserId(results.getInt("user_id"));

            lists.add(list);
        }

        // how do we want to sort lists? alphabetical? not sorted?

        return lists;
    }

    @Override
    public BirdList getList(int listId) {

        String sql = "SELECT list_id, list_name, user_id " +
                "FROM lists " +
                "WHERE list_id = ?";
        SqlRowSet result = template.queryForRowSet(sql, listId);

        BirdList list = null;
        if (result.next()) {
            list = new BirdList();
            list.setListId(result.getInt("list_id"));
            list.setListName(result.getString("list_name"));
            list.setUserId(result.getInt("user_id"));
        }
        return list;
    }

    @Override
    public BirdList addList(BirdList list) {

        String sql = "INSERT INTO lists(list_name, user_id) " +
                "VALUES(?, ?) RETURNING list_id";
        int listId = template.queryForObject(sql, Integer.class,
                list.getListName(), list.getUserId());

        list.setListId(listId);
        return list;
    }

    @Override
    public BirdList updateList(BirdList list) {

        String sql = "UPDATE lists " +
                "SET list_name = ? " +
                "WHERE list_id = ?";
        template.update(sql, list.getListName(), list.getListId());

        return list;
    }

    @Override
    public void deleteList(int listId) {
        String sql = "DELETE FROM lists WHERE list_id = ?";
        template.update(sql, listId);
    }
}
