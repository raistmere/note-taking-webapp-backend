package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.NoteModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDaoImpl implements NoteDao {

    JdbcTemplate jdbcTemplate;

    public NoteDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NoteModel> getAllNotesFromUserID(Long userID) {

        String sql = "select * from notes where userID = ?";

        // we might be able to create a special instance of this that anyone can use instead of creating it each time we want something from DB
        RowMapper<NoteModel> rowMapper = new RowMapper<NoteModel>() {


            @Override
            public NoteModel mapRow(ResultSet rs, int rowNum) throws SQLException {

                // create a new note
                NoteModel note = new NoteModel();

                // apply the ResultSet to note fields
                note.setId(rs.getInt("id"));
                note.setUserId(rs.getInt("userID"));
                note.setTitle(rs.getString("title"));
                note.setNote(rs.getString("note"));

                return note;
            }
        };

        List<NoteModel> noteList = jdbcTemplate.query(sql, rowMapper, userID);

        return noteList;
    }
}
