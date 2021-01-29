package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/file")
public class FileController {

    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }



    @PostMapping("/uploadFile")
    public String handleFileUpload(Principal principal, @RequestParam("fileUpload") MultipartFile fileUpload,
                                   RedirectAttributes redirectAttributes, Model model) throws IOException {

        String UploadError = null;
        User user = userService.getUser(principal.getName());
        File occuringFile =  fileService.getFilebyName(fileUpload.getOriginalFilename());
        if(occuringFile != null){
            UploadError = "The File already exists.";
            redirectAttributes.addFlashAttribute("UploadError", UploadError);

            }



        else {

            File file = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), user.getUserId(), fileUpload.getBytes());

            int row = fileService.createFile(file);
            if (row < 0) {
                redirectAttributes.addFlashAttribute("Error", true);
            } else {
                redirectAttributes.addFlashAttribute("Success", true);
            }
            return "redirect:/result";

        }
        return "redirect:/home";
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer id){
        File file = fileService.getFilebyId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getFileName()+"\"")
                .body(new ByteArrayResource(file.getFileData()));
    }

    @GetMapping("/deleteFile/{fileName}")

    public String deleteFile(@PathVariable String fileName, RedirectAttributes redirectAttributes){
        File file = fileService.getFilebyName(fileName);
        try{
            fileService.deleteFile(file.getFileName());
            redirectAttributes.addFlashAttribute("Success", true);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Error", true);
        }

        return "redirect:/result";
    }




}
