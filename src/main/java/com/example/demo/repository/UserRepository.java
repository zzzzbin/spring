package com.example.demo.repository;

import com.example.demo.domain.user.model.MUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<MUser, String> {

    @Query("select user from MUser user where user.userId = :userId")
    MUser findLoginUser(@Param("userId") String userId);

    @Modifying
    @Query("update MUser set password=:password," +
            "userName = :userName " +
            "where userId = :userId")
    void updateUser(@Param("userId") String userId,
                    @Param("password") String password,
                    @Param("userName") String userName);
}
