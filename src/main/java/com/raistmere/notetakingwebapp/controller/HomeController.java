package com.raistmere.notetakingwebapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raistmere.notetakingwebapp.dto.NoteDto;
import com.raistmere.notetakingwebapp.model.NoteModel;
import com.raistmere.notetakingwebapp.service.NoteServiceImpl;
import com.raistmere.notetakingwebapp.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@RestController()
public class HomeController {

    private final NoteServiceImpl noteServiceImpl;
    UserServiceImpl userService;
    NoteServiceImpl noteService;

    public HomeController(UserServiceImpl userService, NoteServiceImpl noteService, NoteServiceImpl noteServiceImpl) {

        this.userService = userService;
        this.noteService = noteService;
        this.noteServiceImpl = noteServiceImpl;
    }

    @GetMapping("/")
    public String getHome() {

        return "Welcome to the Home Page";
    }

    @GetMapping("/dashboard")
    public String getDashboard(Authentication authentication) throws JsonProcessingException {

        // get username from authentication to ensure that we have the authenticated/authorized user for this session
        String username = authentication.getName();

        // get user id
        Long userID = userService.getUserID(username);

        // get user's notes
        List<NoteModel> noteList = noteService.loadAllUserNotes(userID);

        // convert to json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(noteList);

        System.out.println(json);

        return json;
    }

    @PostMapping("/createnote")
    public String createNote(@RequestBody NoteDto requestBody) {

        System.out.println("REQUEST BODY: " + requestBody);

        // get user id so we can attach it to the new note;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.getUserID(username);

        // create a new note model and use the requestBody to create a new note
        System.out.println("Creating Note...");
        NoteModel noteModel = new NoteModel();
        noteModel.setUserId(requestBody.getUserId());
        noteModel.setTitle(requestBody.getTitle());
        noteModel.setNote(requestBody.getNote());

        // pass the new note model to the note service to handle business logic
        // service should return success or failure (or it can return a new updated note model with database id)
         return noteService.createNote(noteModel, userId);

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
}
