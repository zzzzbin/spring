package com.example.demo.controller;

import com.example.demo.application.service.UserApplicationService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignUpController {
    @Autowired
    private UserApplicationService userApplicationService;

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
    @PostMapping("/singup")
    public String postSignup(Model model, Locale locale,
                             @Validated(GroupOrder.class) @ModelAttribute SignupForm form,
                             BindingResult bindingResult) {
        //input check result
        if (bindingResult.hasErrors()){
            //NG: return the user signup screen
            return getSignup(model, locale, form);
        }
        log.info(form.toString());
        //model.addAttribute("signupForm", form)
        return "redirect:/login"; //PRG
    }

}
