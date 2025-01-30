package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.model.NoteModel;

import java.util.List;

public interface NoteService {

    NoteModel findNoteById(long id);
    List<NoteModel> loadAllUserNotes(Long userId);
    String createNote(NoteModel note, Long userId);
    String deleteNoteById(long noteId);
    String editNoteById(long noteId, NoteModel note);
}
