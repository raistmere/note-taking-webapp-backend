package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.NoteModel;

import java.sql.SQLException;
import java.util.List;

public interface NoteDao {

    NoteModel getNoteById(long id);
    List<NoteModel> getAllNotesFromUserID(Long userID);
    void saveNote(NoteModel note, Long userID);
    void deleteNoteById(long id);
    void editNoteById(long id, NoteModel note);
}
