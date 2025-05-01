package com.example.demo6.dao;


import com.example.demo6.dto.*;
import com.example.demo6.entity.*;
import jakarta.validation.constraints.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface MemberDao {
    @Select("SELECT COUNT(*) FROM members WHERE username = #{username}")
    boolean existsByUsername(String username);


    int save(Member member);

    @Select("select username from members where email=#{email} and rownum =1")
    Optional<String> findUsernameByEmail(String email);

    @Select("select count(*) from members where username=#{username} and email=#{email} and rownum=1")
    boolean existsByUsernameAndEmail(MemberDto.GeneratePassword dto);

    @Update("update members set password=#{newPassword} where username=#{username}")
    void updatePassword(String username, String newPassword);
}
