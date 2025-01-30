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

    @Override
    public NoteModel findNoteById(long id) {

        System.out.println("Finding note by id: " + id);
        return noteDao.getNoteById(id);
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

    @Override
    public String deleteNoteById(long noteId) {

        System.out.println("Deleting note by id: " + noteId);

        try {

            noteDao.deleteNoteById(noteId);
            System.out.println("Note " + noteId + " has been deleted");
            return "Note has been deleted";
        } catch (Exception e) {

            System.out.println("There has been an error deleting the note");
            return "There has been an error deleting the note";
        }
    }
}
