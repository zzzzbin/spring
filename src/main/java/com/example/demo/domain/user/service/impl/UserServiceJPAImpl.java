package com.example.demo.domain.user.service.impl;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJPAImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void signup(MUser user) {
        //existence check
        boolean exists = userRepository.existsById(user.getUserId());
        if (exists) {
            throw new DataAccessException("User already exists") {
            };
        }

        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        userRepository.save(user);
    }

    @Override
    public List<MUser> getUsers(MUser user) {
        //Search condition
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()//and condition
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)//like clause
                .withIgnoreCase();

        return userRepository.findAll(Example.of(user, exampleMatcher));
    }

    @Override
    public MUser getUserByUserId(String userId) {
        Optional<MUser> option = userRepository.findById(userId);
        MUser mUser = option.orElse(null);
        return mUser;
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        String encryptPassword = passwordEncoder.encode(password);
        userRepository.updateUser(userId, encryptPassword, userName);
    }

    @Override
    public void deleteUserOne(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public MUser getLoginUser(String userId) {
        return userRepository.findLoginUser(userId);
    }
}
