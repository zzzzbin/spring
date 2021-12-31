package com.example.demo.controller;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Slf4j
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
        form.setSalaryList(user.getSalaryList()); //Model mapper cannot copy List

        model.addAttribute("userDetailForm", form);
        return "user/detail";
    }

    //    User update process
    @PostMapping(value = "/detail", params = "update")
    public String updateUser(@ModelAttribute UserDetailForm form, Model model) {
        try{
            //update user
            userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
            //redirect to user list screen
        }catch (Exception e){
            log.error("Error in user update", e);
        }

        return "redirect:/user/list";
    }

    // User delete process
    @PostMapping(value = "/detail", params = "delete")
    public String deleteUser(UserDetailForm form, Model model) {
        userService.deleteUserOne(form.getUserId());
        return "redirect:/user/list";
    }

}
