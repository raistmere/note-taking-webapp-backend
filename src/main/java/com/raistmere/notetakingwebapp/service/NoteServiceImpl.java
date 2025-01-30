package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.NoteDaoImpl;
import com.raistmere.notetakingwebapp.model.NoteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    NoteDaoImpl noteDaoImpl;

    public NoteServiceImpl(NoteDaoImpl noteDao) {

        this.noteDaoImpl = noteDao;
    }

    @Override
    public NoteModel findNoteById(long id) {

        System.out.println("Finding note by id: " + id);
        return noteDaoImpl.getNoteById(id);
    }

    // method that handles loading all user notes
    @Override
    public List<NoteModel> loadAllUserNotes(Long userID) {

        // Get all notes from the database based on the userID
        List<NoteModel> noteList = noteDaoImpl.getAllNotesFromUserID(userID);

        // return note list to the controller
        return noteList;
    }

    @Override
    public String createNote(NoteModel note, Long userID) {

        // use DAO to save note to database
        try {

            noteDaoImpl.saveNote(note, userID);
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

            noteDaoImpl.deleteNoteById(noteId);
            System.out.println("Note " + noteId + " has been deleted");
            return "Note has been deleted";
        } catch (Exception e) {

            System.out.println("There has been an error deleting the note");
            return "There has been an error deleting the note";
        }
    }

    @Override
    public String editNoteById(long noteId, NoteModel note) {

        System.out.println("Editing note by id: " + noteId);

        try {

            noteDaoImpl.editNoteById(noteId, note);
            System.out.println("Note " + noteId + " has been edited");
            return "Note has been edited successfully";
        } catch (Exception e) {

            System.out.println("There has been an error editing the note");
            return "There has been an error editing the note";
        }
    }
}
