package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.NoteModel;

import java.util.List;

public interface NoteDao {

    List<NoteModel> getAllNotesFromUserID(Long userID);
    void saveNote(NoteModel note, Long userID);
}
