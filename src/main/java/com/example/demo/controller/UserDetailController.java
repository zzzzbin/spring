package com.example.demo.controller;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserDetailController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm form, Model model,
                          @PathVariable("userId") String userId) {
        MUser user = userService.getUserByUserId(userId);

        form = modelMapper.map(user, UserDetailForm.class);
        model.addAttribute("userDetailForm", form);
        return "user/detail";
    }
}
