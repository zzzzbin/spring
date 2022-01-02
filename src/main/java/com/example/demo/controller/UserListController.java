package com.example.demo.controller;

import com.example.demo.application.service.UserApplicationService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * Display user list screen
     */
    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {
        //convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    //    User search process
    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {
        //convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    /** User list download process */
    @PostMapping("/list/download")
    public ResponseEntity<byte[]> downloadUserList(@ModelAttribute UserListForm form)
            throws IOException {
        // Convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        // User search
        List<MUser> userList = userService.getUsers(user);
        // Save CSV file
        String fileName = "user.csv";
        userApplicationService.saveUserCsv(userList, fileName);
        // Get CSV file
        byte[] bytes = userApplicationService.getCsv(fileName);
        HttpHeaders header = new HttpHeaders();
        // HTTP header settings
        header.add("Content-Type", MediaType.ALL_VALUE + "; charset=utf-8");
        header.setContentDispositionFormData("filename", fileName);
        return new ResponseEntity<>(bytes, header, HttpStatus.OK); }
}
