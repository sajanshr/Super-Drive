package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentailMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentailMapper credentailMapper;

    public CredentialService(CredentailMapper credentailMapper) {
        this.credentailMapper = credentailMapper;
    }

    public int createCredential(Credential credential){

        /*(url, username, key, password,userid*/
        return credentailMapper.createCredential(new Credential(null, credential.getUrl(), credential.getUserName(),  credential.getKey(), credential.getPassword(), credential.getUserId()));

    }



    public Credential getCredentialbyId(Integer credentialId){
        return credentailMapper.getCredentialbyId(credentialId);
    }


    public List<Credential> getAllCredentials(){
        return credentailMapper.getAllCredentials();
    }
    public void deleteCredential(Credential credential){
        credentailMapper.deleteCredential(credential);
    }

    public boolean updateCredential(Credential credential){
        try {
            credentailMapper.updateCredential(credential);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
