package com.example.demo6.dao;

import com.example.demo6.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface PostDao {
    // SelectKey의 실행 결과를 save의 파이미터인 post에 저장한다 -> 서비스에서 post.getPno()로  시퀀스값에 접근가능
    int save(Post post);

    //select * from posts order by pno desc offset 시작위치 rows fetch next 개수 rows only

    List<Post> findAll(int pageno, int pagesize);


    int count();


    int increaseReadCnt(int pno);


    Optional<Post> findByPno(int pno);

    Optional<Map<String, Object>> findByPnoWithComments(int pno);
}
