package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Getter
@Setter
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    // should only select one user at a time (if not then there is a duplicate username in the DB)
    @Override
    public UserModel getUserByUsername(String username) {

        String sql = "SELECT * FROM users WHERE name = ?";

        RowMapper<UserModel> rowMapper = (rs, rowNum) -> {

            UserModel userModel = new UserModel();
            userModel.setId(rs.getLong("id"));
            userModel.setName(rs.getString("name"));
            userModel.setPassword(rs.getString("password"));

            return userModel;
        };

        List<UserModel> userModelList = jdbcTemplate.query(sql, rowMapper, username);

        return !userModelList.isEmpty() ? userModelList.getFirst() : null;
    }

    @Override
    public void saveUser(UserModel user) {

        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";

        jdbcTemplate.update(sql, user.getName(), user.getPassword());
    }

    @Override
    public void updatePassword(Long userId, String password) throws SQLException {

        String sql = "UPDATE users SET password = ? WHERE id = ?";

        jdbcTemplate.update(sql, password, userId);
    }

    @Override
    public List<UserModel> getAllUsers() {

        String sql = "select * from users";

        RowMapper<UserModel> rowMapper = new RowMapper<UserModel>() {


            @Override
            public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

                UserModel userModel = new UserModel();
                userModel.setId(rs.getLong("id"));
                userModel.setName(rs.getString("name"));
                userModel.setPassword(rs.getString("password"));

                return userModel;
            }
        };

        List<UserModel> userModelList = jdbcTemplate.query(sql, rowMapper);

        return userModelList;
    }
}
