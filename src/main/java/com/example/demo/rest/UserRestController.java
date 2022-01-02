package com.example.demo.rest;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.SignupForm;
import com.example.demo.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    //User signup
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class) SignupForm form,
          BindingResult bindingResult, Locale locale
         ){
        //Input check result
        if (bindingResult.hasErrors()){
            //Check result: NG
            Map<String, String> errors = new HashMap<>();
            //Get the error message
            for(FieldError error: bindingResult.getFieldErrors()){
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField(), message);
            }
            return new RestResult(90, errors);
        }

        //Convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        userService.signup(user);

        return new RestResult(0, null);
    }

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
