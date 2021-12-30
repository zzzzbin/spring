package com.example.demo.repository;

import com.example.demo.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * User signup
     */
    int insertOne(MUser user);

    List<MUser> findMany(MUser user);

    MUser findOne(String userId);

    void updateOne(@Param("userId") String userId,
                   @Param("password") String password,
                   @Param("userName") String userName);

    int deleteOne(@Param("userId") String userId);


}
