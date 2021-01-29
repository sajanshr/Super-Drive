package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/addNewNote")
    public String addNewNote(Principal principal, @ModelAttribute("note") Note note, Model model, RedirectAttributes redirectAttributes){
        User user = userService.getUser(principal.getName());
           Note existing = noteService.getNote(note.getNoteId());

           if(existing == null) {


               note.setUserId(user.getUserId());
               int row = noteService.createNote(note);
               if (row < 0) {
                   redirectAttributes.addFlashAttribute("Error", true);
               } else {
                   redirectAttributes.addFlashAttribute("Success", true);
               }
               return "redirect:/result";
           }else{
               existing.setNoteTitle(note.getNoteTitle());
               existing.setNoteDescription(note.getNoteDescription());
               boolean row = noteService.updateNote(existing);
               if(row){
                   redirectAttributes.addFlashAttribute("Success", true);
               }else{
                   redirectAttributes.addFlashAttribute("Error", true);
               }
               return "redirect:/result";

           }

        }


    @GetMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        boolean x = noteService.deleteNote(id);
        if(x==true){
            redirectAttributes.addFlashAttribute("Success", true);
        }else{
            redirectAttributes.addFlashAttribute("Error", true);
        }

        return  "redirect:/result";

    }




}
