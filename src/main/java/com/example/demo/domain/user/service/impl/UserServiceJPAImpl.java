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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJPAImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //programmatic transaction
    @Autowired
    PlatformTransactionManager platformTransactionManager;

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

//    @Transactional
//    @Override
//    public void updateUserOne(String userId, String password, String userName) {
//        String encryptPassword = passwordEncoder.encode(password);
//        userRepository.updateUser(userId, encryptPassword, userName);
//    }

    @Override
    public void updateUserOne(String userId, String password, String userName) {
        //create instance
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //settings
        def.setName("Update user");
        def.setReadOnly(false);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // transaction start
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        String encryptPassword = passwordEncoder.encode(password);
        userRepository.updateUser(userId, encryptPassword, userName);
        try {
//            int i = 1 / 0;
        } catch (Exception e) {
            platformTransactionManager.rollback(status);
            throw new DataAccessException("ERROR update", e) {};
        }
        //commit
        platformTransactionManager.commit(status);

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
