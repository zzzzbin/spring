package com.example.demo.domain.user.service.impl;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");
        //Password encryption
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        mapper.insertOne(user);
    }

    @Override
    public List<MUser> getUsers(MUser user) {
        return mapper.findMany(user);
    }

    @Override
    public MUser getUserByUserId(String userId) {
        return mapper.findOne(userId);
    }

    //Update user
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {

        //Password encryption
        String encryptPassword = encoder.encode(password);

        mapper.updateOne(userId, encryptPassword, userName);
        //Raise an exception
//        int i = 1 / 0;

    }

    @Override
    public void deleteUserOne(String userId) {
        mapper.deleteOne(userId);
    }
}
