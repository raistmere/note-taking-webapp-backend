package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.NoteModel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class NoteDaoImpl implements NoteDao {

    private static final Logger logger = LoggerFactory.getLogger(NoteDaoImpl.class);

    JdbcTemplate jdbcTemplate;

    public NoteDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public NoteModel getNoteById(long id) {

        String sql = "select * from notes where id = ?";

        RowMapper<NoteModel> rowMapper = (rs, rowNum) -> {

            NoteModel noteModel = new NoteModel();
            noteModel.setId(rs.getInt("id"));
            noteModel.setTitle(rs.getString("title"));
            noteModel.setUserId(rs.getLong("userId"));
            noteModel.setNote(rs.getString("note"));

            return noteModel;
        };

        List<NoteModel> noteModelList = jdbcTemplate.query(sql, rowMapper, id);

        return !noteModelList.isEmpty() ? noteModelList.getFirst() : null;

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
                note.setUserId(rs.getLong("userID"));
                note.setTitle(rs.getString("title"));
                note.setNote(rs.getString("note"));

                return note;
            }
        };

        List<NoteModel> noteList = jdbcTemplate.query(sql, rowMapper, userID);

        return noteList;
    }

    @Override
    public void saveNote(NoteModel note, Long userID) {

        // create sql to save note to database
        String sql = "INSERT INTO NOTES (userID, title, note) VALUES (?, ?, ?)";


        jdbcTemplate.update(sql, userID, note.getTitle(), note.getNote());
    }

    @Override
    public void deleteNoteById(long id) {

        String sql = "DELETE FROM NOTES WHERE id = ?";

        try {

            jdbcTemplate.update(sql, id);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
    }

    @Override
    public void editNoteById(long id, NoteModel note) {

        String sql = "UPDATE NOTES SET title = ?, note = ? WHERE id = ?";

        try {

            jdbcTemplate.update(sql, note.getTitle(), note.getNote(), id);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
    }


}
