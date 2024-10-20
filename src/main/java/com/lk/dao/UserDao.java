package com.lk.dao;

import com.lk.domain.User;
import com.lk.domain.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user where id=#{id}")
    User findById(String id);

    @Insert("insert into user values(#{id},#{passwordMd5},now(),now())")
    void register(String id, String passwordMd5);

    @Select("select * from video where id in " +
            "(select idVideo from favorite where idUser=#{id})")
    List<Video> getFavorite(String id);
}
