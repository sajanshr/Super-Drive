package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename  = #{fileName}")
    String getFileName(String fileName);

    @Select("SELECT * FROM FILES WHERE filename  = #{fileName}")
    File getFilebyName(String fileName);

    @Select("SELECT * FROM FILES WHERE fileid  = #{fileId}")
    File getFilebyId(Integer fileId);

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Update("UPDATE FILES SET filename = #{fileName}, contenttype = #{contentType}, filesize = #{fileSize}, userid = #{userId}, filedata = #{fileData}  WHERE fileId=#{fileId}")
    void updateFile(File file);

    @Delete("DELETE FROM FILES WHERE filename = #{filename}")
    void deleteFile(String fileName);
}
