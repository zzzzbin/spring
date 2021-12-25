package com.example.demo.repository;

import com.example.demo.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * User signup
     */
    int insertOne(MUser user);
}
