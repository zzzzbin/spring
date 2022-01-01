package com.example.demo.rest;

import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    //Update user
    @PutMapping("/update")
    public int updateUser(UserDetailForm form) {
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
        return 0;
    }

    //Delete user
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form){
        userService.deleteUserOne(form.getUserId());
        return 0;
    }

}
