package com.example.demo.domain.user.service;

import com.example.demo.domain.user.model.MUser;

import java.util.List;

public interface UserService {
    void signup(MUser user);
    List<MUser> getUsers();
}
