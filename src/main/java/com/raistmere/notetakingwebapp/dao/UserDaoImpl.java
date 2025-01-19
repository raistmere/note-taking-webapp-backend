package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Getter
@Setter
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByUsername(String username) {

        return null;
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "select * from users";

        RowMapper<User> rowMapper = new RowMapper<User>() {


            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));

                return user;
            }
        };

        List<User> userList = jdbcTemplate.query(sql, rowMapper);

        return userList;
    }
}
