package com.raistmere.notetakingwebapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.raistmere.notetakingwebapp.dto.ChangePasswordDto;
import com.raistmere.notetakingwebapp.dto.NoteDto;
import com.raistmere.notetakingwebapp.model.NoteModel;
import com.raistmere.notetakingwebapp.model.UserModel;
import com.raistmere.notetakingwebapp.service.NoteServiceImpl;
import com.raistmere.notetakingwebapp.service.UserServiceImpl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController()
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final UserServiceImpl userServiceImpl;
    private final NoteServiceImpl noteServiceImpl;

    public HomeController(UserServiceImpl userService, NoteServiceImpl noteService) {

        this.userServiceImpl = userService;
        this.noteServiceImpl = noteService;
    }

    @GetMapping("/")
    public String getHome() {

        return "Welcome to the Home Page";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserModel user) {

        logger.info(user.toString());

        return userServiceImpl.createUser(user);
    }

    @GetMapping("/dashboard")
    public String getDashboard(Authentication authentication) throws JsonProcessingException {

        // get username from authentication to ensure that we have the authenticated/authorized user for this session
        String username = authentication.getName();

        // get user id
        Long userID = userServiceImpl.getUserID(username);

        // get user's notes
        List<NoteModel> noteList = noteServiceImpl.loadAllUserNotes(userID);

        // convert to json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(noteList);

        return json;
    }

    @PostMapping("/createnote")
    public String createNote(@RequestBody NoteDto requestBody) {

        logger.info("REQUEST BODY: " + requestBody);

        // get user id so we can attach it to the new note;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userServiceImpl.getUserID(username);

        // create a new note model and use the requestBody to create a new note
        logger.info("Creating Note...");
        NoteModel noteModel = new NoteModel();
        noteModel.setUserId(requestBody.getUserId());
        noteModel.setTitle(requestBody.getTitle());
        noteModel.setNote(requestBody.getNote());

        // pass the new note model to the note service to handle business logic
        // service should return success or failure (or it can return a new updated note model with database id)
         return noteServiceImpl.createNote(noteModel, userId);

//        // USING HTTPSERVLETREQUEST INSTEAD OF REQUESTBODY TO SEE HOW THIS WORKS
//        StringBuilder rawBody = new StringBuilder();
//        try (BufferedReader reader = requestBody.getReader()) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//
//                rawBody.append(line);
//            }
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        NoteDto newNote = mapper.readValue(rawBody.toString(), NoteDto.class);
//        System.out.println("REQUEST BODY:" + newNote);
//
//        return "Testing note creation";
    }

    @PostMapping("/deletenote")
    public String deleteNote(@RequestBody NoteDto requestBody) {

        logger.info("REQUEST BODY: " + requestBody);

        // get note (that is about to be deleted) note id
        long noteId = requestBody.getId();

        // get current auth user id
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long userId = userServiceImpl.getUserID(username);

        // check if note (that is about to be deleted) belongs to the auth user (prevents people from deleting other users notes)
        NoteModel noteToBeDeleted = noteServiceImpl.findNoteById(noteId);
        if(noteToBeDeleted == null) return "Failed to find note!";
        if(!noteToBeDeleted.getUserId().equals(userId)) return "Note does not belong to user!";

        // if everything is good and valid, then call note service for deletion
        return noteServiceImpl.deleteNoteById(noteId);
    }

    @PostMapping("/editnote")
    public String editNote(@RequestBody NoteDto requestBody) {

        logger.info("REQUEST BODY: " + requestBody);

        logger.info("Editing note with new changes...");

        // get note (that is about to be edited) note id
        long noteId = requestBody.getId();

        // get current auth user id
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long userId = userServiceImpl.getUserID(username);

        // TODO: Should everything below be handled by noteServiceImpl instead of controller?
        // check if note (that is about to be deleted) belongs to the auth user (prevents people from deleting other users notes)
        NoteModel noteToBeEdited = noteServiceImpl.findNoteById(noteId);
        if(noteToBeEdited == null) return "Failed to find note!";
        if(!noteToBeEdited.getUserId().equals(userId)) return "Note does not belong to user!";

        // create a new note model using requestBody data to overwrite the old note in the database
        // we only want to edit the title and the note for now. There should not be any edit on the id, userId.
        NoteModel noteModel = new NoteModel();
        noteModel.setTitle(requestBody.getTitle());
        noteModel.setNote(requestBody.getNote());

        // if everything is good and valid, then call note service for deletion
        return noteServiceImpl.editNoteById(noteId, noteModel);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordDto changePasswordDto) {

        logger.info("REQUEST BODY: " + changePasswordDto);

        // TODO: We need to do some type of validation to check if user is authenticated and if they are the correct user. Use the oldPassword to validate.

        // Get user info
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userServiceImpl.getUserID(userName);

        // update password with new password from changePasswordDto
        return userServiceImpl.changePassword(userId, changePasswordDto);
    }
}
