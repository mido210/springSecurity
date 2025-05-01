package com.example.demo6.dao;

import com.example.demo6.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface commentDao {
    @Select("select  * from comments where pno=#{pno}")
    List<Comment> findByPno(int pno);
}
