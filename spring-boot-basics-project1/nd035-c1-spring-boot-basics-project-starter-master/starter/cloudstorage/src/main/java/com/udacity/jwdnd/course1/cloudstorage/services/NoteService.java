package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Integer createNote(Note note){
        return noteMapper.insert(new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
    }

    public Note getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }

    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }


    public boolean updateNote(Note note){
        try{
            noteMapper.updateNote(note);
            return  true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    public boolean deleteNote(Integer noteId){
        try{
            noteMapper.deleteNote(noteId);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }




    }



}
