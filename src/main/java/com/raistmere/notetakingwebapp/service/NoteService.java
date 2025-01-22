package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.model.NoteModel;

import java.util.List;

public interface NoteService {

    List<NoteModel> loadAllUserNotes(Long userId);
}
