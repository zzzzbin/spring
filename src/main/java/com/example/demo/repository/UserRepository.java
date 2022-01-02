package com.example.demo.repository;

import com.example.demo.domain.user.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MUser, String> {

}
