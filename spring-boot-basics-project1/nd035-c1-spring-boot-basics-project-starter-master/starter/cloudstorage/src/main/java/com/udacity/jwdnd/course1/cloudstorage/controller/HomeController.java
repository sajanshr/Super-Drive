package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String HomeView(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("files", fileService.getAllFiles());
        model.addAttribute("credentials", credentialService.getAllCredentials());
        return "home";
    }

}
