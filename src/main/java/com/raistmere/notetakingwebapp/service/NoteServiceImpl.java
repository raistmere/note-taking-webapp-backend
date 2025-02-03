package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.NoteDaoImpl;

import com.raistmere.notetakingwebapp.model.NoteModel;

import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    NoteDaoImpl noteDaoImpl;

    public NoteServiceImpl(NoteDaoImpl noteDao) {

        this.noteDaoImpl = noteDao;
    }

    @Override
    public NoteModel findNoteById(long id) {

        logger.info("Finding note by id: {}", id);
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
            logger.info("Note has been created");

            return "Note has been created";
        } catch (Exception e) {

            logger.error("There has been an error creating the note");

            return "There has been an error creating the note";
        }
    }

    @Override
    public String deleteNoteById(long noteId) {

        logger.info("Deleting note by id: {}", noteId);

        try {

            noteDaoImpl.deleteNoteById(noteId);
            logger.info("Note {} has been deleted", noteId);

            return "Note has been deleted";
        } catch (Exception e) {

            logger.error("There has been an error deleting the note {}", noteId);

            return "There has been an error deleting the note";
        }
    }

    @Override
    public String editNoteById(long noteId, NoteModel note) {

        System.out.println("Editing note by id: " + noteId);
        logger.info("Editing note by id: {}",noteId);

        try {

            noteDaoImpl.editNoteById(noteId, note);
            logger.info("Note {} has been edited", noteId);

            return "Note has been edited successfully";
        } catch (Exception e) {

            logger.error("There has been an error editing the note {}", noteId);

            return "There has been an error editing the note";
        }
    }
}
