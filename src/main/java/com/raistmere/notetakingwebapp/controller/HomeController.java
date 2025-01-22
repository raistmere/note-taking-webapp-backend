package com.raistmere.notetakingwebapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raistmere.notetakingwebapp.model.NoteModel;
import com.raistmere.notetakingwebapp.service.NoteServiceImpl;
import com.raistmere.notetakingwebapp.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class HomeController {

    UserServiceImpl userService;
    NoteServiceImpl noteService;

    public HomeController(UserServiceImpl userService,NoteServiceImpl noteService) {

        this.userService = userService;
        this.noteService = noteService;
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
}
