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

        // return note list to the controller
        return noteList;
    }

    @Override
    public String createNote(NoteModel note, Long userID) {

        // use DAO to save note to database
        try {

            noteDao.saveNote(note, userID);
            System.out.println("Note has been created");
            return "Note has been created";
        } catch (Exception e) {

            System.out.println("There has been an error creating the note");
            return "There has been an error creating the note";
        }
    }
}
