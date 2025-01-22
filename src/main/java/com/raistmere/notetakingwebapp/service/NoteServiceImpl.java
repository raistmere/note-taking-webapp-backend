package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.NoteDaoImpl;
import com.raistmere.notetakingwebapp.model.NoteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    NoteDaoImpl noteDao;

    public NoteServiceImpl(NoteDaoImpl noteDao) {

        this.noteDao = noteDao;
    }

    // method that handles loading all user notes
    @Override
    public List<NoteModel> loadAllUserNotes(Long userID) {

        // Get all notes from the database based on the userID
        List<NoteModel> noteList = noteDao.getAllNotesFromUserID(userID);

        // return notelist to the controller
        return noteList;
    }
}
