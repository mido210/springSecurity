package com.example.demo6.service;


import com.example.demo6.dao.*;
import com.example.demo6.dto.*;
import com.example.demo6.entity.*;
import com.example.demo6.util.*;
import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    private PasswordEncoder encoder;

    public boolean checkUsername(MemberDto.UsernameCheck dto) {
       return !memberDao.existsByUsername(dto.getUsername());
    }

    public Member signup(MemberDto.Create dto){
        //DTO는 화면따라간다. Entity는 db따라간다
        //비밀번호 암호화 했다 치자 1순위
        String encodedPassword = encoder.encode(dto.getPassword()) ;
        // 프사 저장을 위한 base64 인코딩
        MultipartFile profile = dto.getProfile();
//        <input type ="file" name='profile'> 선택 안하고 넘어갔다 -> null이 아니다
        String base64Image="";
        if(!profile.isEmpty()){
            try {
             base64Image = Demo6Util.convertToBase64(profile);
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        //3. 암화된 비밀번호, base64이미지를 가지고 dto를 member로
        Member member = dto.toEntity(encodedPassword, base64Image);
        memberDao.save(member);
        return member;
    }
    public Optional<String> searchUsername(String email){
     return memberDao.findUsernameByEmail(email);
    }

    public Optional<String> getTemporaryPassword(MemberDto.GeneratePassword dto){
        //1. 아이디와 이메일이 일치하는 사용자가 있는지 확인
        //2. 있을 경우 임시비밀번호 생성
        //3. 임시비밀번호를 암호화해서 업데이트
        //4. 비밀번호 리턴
            boolean isExist= memberDao.existsByUsernameAndEmail(dto);
            if(!isExist){
                return Optional.empty();
            }
            String newPassword = RandomStringUtils.secure().nextAlphanumeric(20);
            memberDao.updatePassword(dto.getUsername(), newPassword);
            return Optional.ofNullable(newPassword);
    }


}
