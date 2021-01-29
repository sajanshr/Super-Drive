package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/addCredential")
    public String addCredential(Principal principal, @ModelAttribute("credential") Credential credential, Model model, RedirectAttributes redirectAttributes) {
        User user = userService.getUser(principal.getName());
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);


        credential.setUserId(user.getUserId());
        credential.setPassword(encryptedPassword);
        int row = credentialService.createCredential(credential);
        if (row < 0) {
            redirectAttributes.addFlashAttribute("Error", true);
        } else {
            redirectAttributes.addFlashAttribute("Success", true);
        }
        return "redirect:/result";

    }

    @GetMapping("/editCredential/{id}")
    public String editCredential(@PathVariable Integer id, @ModelAttribute("credential") Credential credential, Model model, RedirectAttributes redirectAttributes) {
        Credential prevailingCredential = credentialService.getCredentialbyId(id);
        prevailingCredential.setUrl(credential.getUrl());

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        prevailingCredential.setPassword(encryptedPassword);
        boolean x = credentialService.updateCredential(prevailingCredential);
        if(x==true){
            redirectAttributes.addFlashAttribute("Success", true);
        }else{
            redirectAttributes.addFlashAttribute("Error", true);
        }
        return "redirect:/result";

    }

}
