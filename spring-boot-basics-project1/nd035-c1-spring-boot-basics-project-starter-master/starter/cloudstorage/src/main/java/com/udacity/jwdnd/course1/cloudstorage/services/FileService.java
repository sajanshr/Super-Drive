package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(File file){
        return fileMapper.insert(new File(null, file.getFileName(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getFileData()));
    }

    public String getFileName(String fileName){
        return fileMapper.getFileName(fileName);
    }
    public File getFilebyId(Integer fileId){
        return fileMapper.getFilebyId(fileId);
    }
    public File getFilebyName(String fileName){
        return fileMapper.getFilebyName(fileName);
    }

    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }
    public void deleteFile(String fileName){
        fileMapper.deleteFile(fileName);
    }

    public void updateFile(File file){
        fileMapper.updateFile(file);
    }


}
