package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentailMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredentialbyId(Integer credentailId);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAllCredentials();

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password,userid) VALUES(#{Url}, #{userName}, #{Key}, #{Password},#{userId} )")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer createCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, key = #{Key}, password = #{password}, userid = #{userId},   WHERE credentialid=#{credentialId}")
    void updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Credential credential);


}
