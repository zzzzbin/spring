package com.example.demo.controller;

import com.example.demo.application.service.UserApplicationService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignUpController {
    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Display user signup screen
     */
    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale,
                            @ModelAttribute SignupForm form) {
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    /**
     * User signup process
     */
    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
                             @Validated(GroupOrder.class) @ModelAttribute SignupForm form,
                             BindingResult bindingResult) {
        //input check result
        if (bindingResult.hasErrors()) {
            //NG: return the user signup screen
            return getSignup(model, locale, form);
        }
        log.info(form.toString());
        //model.addAttribute("signupForm", form)

        //convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);

        //signup: do not pass form classes to service
        userService.signup(user);
        return "redirect:/login"; //PRG
    }

    /**
     * Database-related exception handling
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {
        // Set an empty string
        model.addAttribute("error", "");
        // Register message in Model
        model.addAttribute("message", "An exception occurred in SignupController");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }

    /**
     * Other exception handling
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        // Set an empty string
        model.addAttribute("error", "");
        // Register message in Model
        model.addAttribute("message", "An exception occurred in SignupController");
        // Register HTTP error code(500) in Model
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
}
